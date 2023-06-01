package com.example.sprint16_architecture.domain.impl

import com.example.sprint16_architecture.domain.api.MoviesInteractor
import com.example.sprint16_architecture.domain.api.MoviesRepository
import com.example.sprint16_architecture.domain.models.Movie
import com.example.sprint16_architecture.data.network.Resource
import java.util.concurrent.Executors

class MoviesInteractorImpl(private val repository: MoviesRepository) : MoviesInteractor {

    private val executor = Executors.newCachedThreadPool()

    override fun getDataFromApi(expression: String, consumer: MoviesInteractor.Consumer) {
        executor.execute {
            when(val resource = repository.searchMovies(expression)) {
                is Resource.Success -> { consumer.consume(resource.data, null) }
                is Resource.Error -> { consumer.consume(null, resource.message) }
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