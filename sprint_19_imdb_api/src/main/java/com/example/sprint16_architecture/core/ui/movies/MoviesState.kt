package com.example.sprint16_architecture.core.ui.movies

import com.example.sprint16_architecture.core.domain.models.Movie

sealed interface MoviesState {

    object Loading : MoviesState

    data class Content(val movies: List<Movie>) : MoviesState

    data class Error(val errorMessage: String) : MoviesState

    data class Empty(val message: String) : MoviesState

}