package com.example.sprint16_architecture.core.domain.impl

import com.example.sprint16_architecture.core.data.network.Resource
import com.example.sprint16_architecture.core.domain.api.MoviesInteractor
import com.example.sprint16_architecture.core.domain.api.MoviesRepository
import com.example.sprint16_architecture.core.domain.models.Movie
import com.example.sprint16_architecture.core.domain.models.SearchType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

class MoviesInteractorImpl(
    private val repository: MoviesRepository,
) : MoviesInteractor {

    private val coroutineScope = CoroutineScope(Job() + Dispatchers.IO)

    override fun getDataFromApi(expression: String, type: SearchType, consumer: MoviesInteractor.Consumer) {
        coroutineScope.launch {
            val resource = when(type) {
                SearchType.DETAILS -> repository.searchDetails(expression)
                SearchType.CAST -> repository.searchMovieCast(expression)
            }
            when(resource) {
                is Resource.Success -> { consumer.consume(resource.data, null) }
                is Resource.Error -> { consumer.consume(null, resource.message) }
            }
        }
    }

    override fun getMovies(expression: String, consumer: MoviesInteractor.Consumer) {
        coroutineScope.launch {
            repository.searchMovies(expression).collect{
                when(it) {
                    is Resource.Success -> { consumer.consume(it.data, null) }
                    is Resource.Error -> { consumer.consume(null, it.message) }
                }
            }

        }

    }

    override fun addMovieToFavorites(movie: Movie) {
        repository.addMovieToFavorites(movie)
    }

    override fun removeMovieFromFavorites(movie: Movie) {
        repository.removeMovieFromFavorites(movie)
    }
}