package com.example.sprint16_architecture.core.ui.cast

import com.example.sprint16_architecture.core.ui.Cell


sealed interface MoviesCastState {

    object Loading : MoviesCastState

    data class Content(
        val fullTitle: String,
        val items: List<Cell>,
    ) : MoviesCastState

    data class Error(
        val message: String,
    ) : MoviesCastState

}