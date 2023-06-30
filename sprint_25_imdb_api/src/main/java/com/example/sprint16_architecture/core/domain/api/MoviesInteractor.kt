package com.example.sprint16_architecture.core.domain.api

import com.example.sprint16_architecture.core.domain.models.Movie
import com.example.sprint16_architecture.core.domain.models.SearchType

interface MoviesInteractor {

    fun getDataFromApi(expression: String, type: SearchType, consumer: Consumer)
    fun addMovieToFavorites(movie: Movie)
    fun removeMovieFromFavorites(movie: Movie)

    interface Consumer{
        fun <T> consume(data: T?, errorMessage: String?)
    }

}