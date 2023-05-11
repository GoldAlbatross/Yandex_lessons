package com.example.sprint16_architecture.presentation.movies

import com.example.sprint16_architecture.domain.models.Movie

interface MoviesView {

    fun showPlaceholderMessage(isVisible: Boolean)

    fun showMoviesList(isVisible: Boolean)

    fun showProgressBar(isVisible: Boolean)

    fun showToast(message: String)

    fun changePlaceholderText(newPlaceholderText: String)

    fun updateMoviesList(newMoviesList: List<Movie>)

}