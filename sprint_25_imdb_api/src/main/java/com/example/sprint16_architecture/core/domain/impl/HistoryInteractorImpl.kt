package com.example.sprint16_architecture.core.domain.impl

import com.example.sprint16_architecture.core.domain.api.HistoryInteractor
import com.example.sprint16_architecture.core.domain.api.HistoryRepository
import com.example.sprint16_architecture.core.domain.models.Movie
import kotlinx.coroutines.flow.Flow

class HistoryInteractorImpl(
    private val repository: HistoryRepository,
): HistoryInteractor {

    override fun historyMovies(): Flow<List<Movie>> {
        return repository.historyMovies()
    }

}