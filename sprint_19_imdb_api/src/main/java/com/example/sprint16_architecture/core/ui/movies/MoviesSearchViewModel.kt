package com.example.sprint16_architecture.core.ui.movies

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.sprint16_architecture.R
import com.example.sprint16_architecture.core.domain.api.MoviesInteractor
import com.example.sprint16_architecture.core.domain.models.Movie
import com.example.sprint16_architecture.core.domain.models.SearchType


class MoviesSearchViewModel(
    application: Application,
    private val moviesInteractor: MoviesInteractor,
): AndroidViewModel(application) {

    private val handler = Handler(Looper.getMainLooper())
    private val stateLiveData = MutableLiveData<MoviesState>()
    private val toastState = MutableLiveData<ToastState>(ToastState.None)
    private val mediatorStateLiveData = MediatorLiveData<MoviesState>().also { liveData ->
        liveData.addSource(stateLiveData) { movieState ->
            liveData.value = when (movieState) {
                is MoviesState.Content -> MoviesState.Content(movieState.movies.sortedByDescending { it.inFavorite })
                is MoviesState.Empty -> movieState
                is MoviesState.Error -> movieState
                is MoviesState.Loading -> movieState
            }
        }
    }

    private var latestSearchText: String? = null
    fun observeViewState(): LiveData<MoviesState> = mediatorStateLiveData
    fun observeToastState(): LiveData<ToastState> = toastState
    override fun onCleared() {
        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)
    }

    fun searchDebounce(changedText: String) {
        if (latestSearchText == changedText) {
            return
        }

        latestSearchText = changedText
        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)
        val searchRunnable = Runnable { searchRequest(changedText) }
        val postTime = SystemClock.uptimeMillis() + SEARCH_DEBOUNCE_DELAY
        handler.postAtTime(
            searchRunnable,
            SEARCH_REQUEST_TOKEN,
            postTime,
        )
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            renderState(MoviesState.Loading)

            moviesInteractor.getDataFromApi(
                expression = newSearchText,
                type = SearchType.MOVIES,
                consumer = object : MoviesInteractor.Consumer {
                override fun <T> consume(data: T?, errorMessage: String?) {

                    val response = mutableListOf<Movie>()
                    if (data != null) { response.addAll(data as MutableList<Movie>) }

                    when {
                        errorMessage != null -> {
                            renderState(
                                MoviesState.Error(
                                    errorMessage = getApplication<Application>().getString(R.string.something_went_wrong),
                                )
                            )
                        }

                        response.isEmpty() -> {
                            renderState(
                                MoviesState.Empty(
                                    message = getApplication<Application>().getString(R.string.nothing_found),
                                )
                            )
                        }

                        else -> {
                            renderState(
                                MoviesState.Content(
                                    movies = response,
                                )
                            )
                        }
                    }
                }
            })
        }
    }

    fun toastWasShown() {
        toastState.value = ToastState.None
    }

    private fun renderState(state: MoviesState) {
        stateLiveData.postValue(state)
    }

    fun toggleFavorite(movie: Movie) {
        if (movie.inFavorite) {
            moviesInteractor.removeMovieFromFavorites(movie)
        } else {
            moviesInteractor.addMovieToFavorites(movie)
        }
        updateMovieContent(movie.id, movie.copy(inFavorite = !movie.inFavorite))
    }

    private fun updateMovieContent(movieId: String, newMovie: Movie) {
        val currentState = stateLiveData.value

        if (currentState is MoviesState.Content) {
            val movieIndex = currentState.movies.indexOfFirst { it.id == movieId }
            if (movieIndex != -1) {
                stateLiveData.value = MoviesState.Content(
                    currentState.movies.toMutableList().also { it[movieIndex] = newMovie }
                )
            }
        }
    }


    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
        private val SEARCH_REQUEST_TOKEN = Any()
    }

}