package com.example.sprint16_architecture.presentation.movies

import com.example.sprint16_architecture.domain.models.Movie
import com.example.sprint16_architecture.util.MoviesState

interface MoviesView {

    fun showToast(message: String)

    fun render(state: MoviesState)
    fun showLoading()
    fun showError(errorMessage: String)
    fun showEmpty(emptyMessage: String)
    fun showContent(movies: List<Movie>)

}