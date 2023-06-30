package com.example.sprint16_architecture.core.ui.details

import com.example.sprint16_architecture.core.domain.models.MovieDetails

sealed interface AboutState {

    data class Content(
        val movie: MovieDetails
    ) : AboutState

    data class Error(
        val message: String
    ) : AboutState

}