package com.example.sprint16_architecture.core.ui.history

import com.example.sprint16_architecture.core.domain.models.Movie

sealed interface HistoryState {

    object Loading : HistoryState

    data class Content(val movies: List<Movie>) : HistoryState

    data class Empty(val message: String) : HistoryState
}