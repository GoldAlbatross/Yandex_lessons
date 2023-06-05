package com.example.sprint16_architecture.domain.api

import com.example.sprint16_architecture.domain.models.Movie
import com.example.sprint16_architecture.domain.models.MovieDetails
import com.example.sprint16_architecture.data.network.Resource
import com.example.sprint16_architecture.domain.models.MovieCast
import com.example.sprint16_architecture.domain.models.SearchType

interface MoviesRepository {

    fun searchMovies(expression: String): Resource<List<Movie>>
    fun searchDetails(movieId: String): Resource<MovieDetails>
    fun searchMovieCast(movieId: String): Resource<MovieCast>
    fun addMovieToFavorites(movie: Movie)
    fun removeMovieFromFavorites(movie: Movie)
}
