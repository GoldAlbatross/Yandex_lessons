package com.example.sprint16_architecture.core.domain.api

import com.example.sprint16_architecture.core.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface HistoryInteractor {

    fun historyMovies(): Flow<List<Movie>>
}