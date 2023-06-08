package com.example.sprint16_architecture.core.domain.api

import com.example.sprint16_architecture.core.data.network.Resource
import com.example.sprint16_architecture.core.domain.models.Movie
import com.example.sprint16_architecture.core.domain.models.MovieCast
import com.example.sprint16_architecture.core.domain.models.MovieDetails

interface MoviesRepository {

    fun searchMovies(expression: String): Resource<List<Movie>>
    fun searchDetails(movieId: String): Resource<MovieDetails>
    fun searchMovieCast(movieId: String): Resource<MovieCast>
    fun addMovieToFavorites(movie: Movie)
    fun removeMovieFromFavorites(movie: Movie)
}
