package com.example.sprint16_architecture.domain.api

import com.example.sprint16_architecture.domain.models.Movie
import com.example.sprint16_architecture.util.Resource

interface MoviesRepository {

    fun searchMovies(expression: String): Resource<List<Movie>>
}
