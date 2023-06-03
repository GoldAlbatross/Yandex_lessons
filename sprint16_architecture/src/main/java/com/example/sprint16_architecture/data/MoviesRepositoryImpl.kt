package com.example.sprint16_architecture.data

import com.example.sprint16_architecture.data.dto.MovieDetailsRequest
import com.example.sprint16_architecture.data.dto.MovieDetailsResponse
import com.example.sprint16_architecture.data.dto.MoviesSearchRequest
import com.example.sprint16_architecture.data.dto.MoviesSearchResponse
import com.example.sprint16_architecture.data.shared_pref.LocalStorage
import com.example.sprint16_architecture.data.network.NetworkClient
import com.example.sprint16_architecture.domain.api.MoviesRepository
import com.example.sprint16_architecture.domain.models.Movie
import com.example.sprint16_architecture.domain.models.MovieDetails
import com.example.sprint16_architecture.data.network.Resource

class MoviesRepositoryImpl(
    private val networkClient: NetworkClient,
    private val localStorage: LocalStorage,
) : MoviesRepository {

    override fun searchMovies(expression: String): Resource<List<Movie>> {

        val response = networkClient.doRequest(MoviesSearchRequest(expression))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                val stored = localStorage.getSavedFavorites()
                Resource.Success((response as MoviesSearchResponse).results.map {
                    Movie(it.id, it.resultType, it.image, it.title, it.description, stored.contains(it.id))})
            }
            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }
    override fun searchDetails(movieId: String): Resource<MovieDetails> {

        val response = networkClient.doRequest(MovieDetailsRequest(movieId))
        return when (response.resultCode) {
            -1 -> Resource.Error("Проверьте подключение к интернету")
            200 -> {
                with(response as MovieDetailsResponse) {
                    Resource.Success(MovieDetails(id, title, imDbRating, year,
                        countries, genres, directors, writers, stars, plot))
                }
            }
            else -> {
                Resource.Error("Ошибка сервера", null)
            }
        }
    }

    override fun addMovieToFavorites(movie: Movie) {
        localStorage.addToFavorites(movie.id)
    }

    override fun removeMovieFromFavorites(movie: Movie) {
        localStorage.removeFromFavorites(movie.id)
    }
}