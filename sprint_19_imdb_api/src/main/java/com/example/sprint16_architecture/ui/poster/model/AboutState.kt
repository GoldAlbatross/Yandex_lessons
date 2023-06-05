package com.example.sprint16_architecture.ui.poster.model

import com.example.sprint16_architecture.domain.models.MovieDetails

sealed interface AboutState {

    data class Content(
        val movie: MovieDetails
    ) : AboutState

    data class Error(
        val message: String
    ) : AboutState

}