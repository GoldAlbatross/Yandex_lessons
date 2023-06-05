package com.example.sprint16_architecture.ui.cast


sealed interface MoviesCastState {

    object Loading : MoviesCastState

    data class Content(
        val fullTitle: String,
        val items: List<MovieCastRVItem>,
        ) : MoviesCastState

    data class Error(val message: String) : MoviesCastState

}