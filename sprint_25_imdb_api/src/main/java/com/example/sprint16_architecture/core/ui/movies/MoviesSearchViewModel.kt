package com.example.sprint16_architecture.core.ui.movies

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.sprint16_architecture.R
import com.example.sprint16_architecture.core.domain.api.MoviesInteractor
import com.example.sprint16_architecture.core.domain.models.Movie
import com.example.sprint16_architecture.core.domain.models.SearchType
import com.example.sprint16_architecture.core.ui.details.DetailsFragment
import com.example.sprint16_architecture.utils.debounce


class MoviesSearchViewModel(
    application: Application,
    private val moviesInteractor: MoviesInteractor,
): AndroidViewModel(application) {

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

    private val searchDebouncer = debounce<String>(
    delayMillis = SEARCH_DEBOUNCE_DELAY,
    coroutineScope = viewModelScope,
    useLastParam = true
    ) { query -> searchRequest(query) }

    fun searchDebounce(changedText: String) {
        if (latestSearchText == changedText) return

        latestSearchText = changedText
        searchDebouncer(changedText)


    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            renderState(MoviesState.Loading)

            moviesInteractor.getMovies(
                expression = newSearchText,
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
    }

}