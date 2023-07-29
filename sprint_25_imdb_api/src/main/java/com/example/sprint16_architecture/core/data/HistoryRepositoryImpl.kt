package com.example.sprint16_architecture.core.data

import com.example.sprint16_architecture.core.data.converters.MovieDbConvertor
import com.example.sprint16_architecture.core.data.storage.db.AppDatabase
import com.example.sprint16_architecture.core.domain.api.HistoryRepository
import com.example.sprint16_architecture.core.domain.models.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HistoryRepositoryImpl(
    private val database: AppDatabase,
    private val dbConvertor: MovieDbConvertor,
): HistoryRepository {

    override fun historyMovies(): Flow<List<Movie>> = flow {
        val movies = database.movieDao().getMovies()
        emit(movies.map { dbConvertor.map(it) })
    }
}