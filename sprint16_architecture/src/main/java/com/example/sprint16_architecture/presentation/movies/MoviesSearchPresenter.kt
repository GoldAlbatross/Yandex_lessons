package com.example.sprint16_architecture.presentation.movies

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import com.example.sprint16_architecture.R
import com.example.sprint16_architecture.util.Creator
import com.example.sprint16_architecture.domain.api.MoviesInteractor
import com.example.sprint16_architecture.domain.models.Movie

class MoviesSearchPresenter(
    private val view: MoviesView,
    private val context: Context,
) {
    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
        private val SEARCH_REQUEST_TOKEN = Any()
    }
    private val moviesInteractor = Creator.provideMoviesInteractor(context)
    private val movies = ArrayList<Movie>()
    private val handler = Handler(Looper.getMainLooper())

    fun searchDebounce(changedText: String) {
        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)
        val searchRunnable = Runnable { searchRequest(changedText) }
        val postTime = SystemClock.uptimeMillis() + SEARCH_DEBOUNCE_DELAY
        handler.postAtTime(searchRunnable, SEARCH_REQUEST_TOKEN, postTime)
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            view.showPlaceholderMessage(isVisible = false)
            view.showMoviesList(isVisible = false)
            view.showProgressBar(isVisible = true)

            moviesInteractor.searchMovies(
                newSearchText,
                object : MoviesInteractor.MoviesConsumer {

                    override fun consume(foundMovies: List<Movie>?, errorMessage: String?) {
                        handler.post {
                            view.showProgressBar(isVisible = false)
                            if (foundMovies != null) {
                                movies.clear()
                                movies.addAll(foundMovies)
                                view.updateMoviesList(movies)
                                view.showMoviesList(isVisible = true)
                            }
                            if (errorMessage != null) {
                                showMessage(context.getString(R.string.something_went_wrong), errorMessage)
                            } else if (movies.isEmpty()) {
                                showMessage(context.getString(R.string.nothing_found), "")
                            } else {
                                hideMessage()
                            }
                        }
                    }
                }
            )
        }
    }


    fun onDestroy() {
        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)
    }

    private fun showMessage(text: String, additionalMessage: String) {

        if (text.isNotEmpty()) {
            view.showPlaceholderMessage(isVisible = true)
            movies.clear()
            view.updateMoviesList(movies)
            view.changePlaceholderText(text)
            if (additionalMessage.isNotEmpty()) { view.showToast(additionalMessage) }
        } else { view.showPlaceholderMessage(isVisible = false) }
    }

    private fun hideMessage() {
        view.showPlaceholderMessage(isVisible = false)
    }

}