package com.example.sprint16_architecture.domain.api

import com.example.sprint16_architecture.domain.models.Movie
import com.example.sprint16_architecture.util.Resource

interface MoviesInteractor {

    fun searchMovies(expression: String, consumer: MoviesConsumer)
    fun addMovieToFavorites(movie: Movie)
    fun removeMovieFromFavorites(movie: Movie)

    interface MoviesConsumer {
        fun consume(foundMovies: List<Movie>?, errorMessage: String?)
    }

}