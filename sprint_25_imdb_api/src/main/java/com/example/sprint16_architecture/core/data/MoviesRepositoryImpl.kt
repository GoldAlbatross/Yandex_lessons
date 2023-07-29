package com.example.sprint16_architecture.core.data

import com.example.sprint16_architecture.core.data.converters.MovieCastConverter
import com.example.sprint16_architecture.core.data.converters.MovieDbConvertor
import com.example.sprint16_architecture.core.data.dto.cast.MovieCastRequest
import com.example.sprint16_architecture.core.data.dto.cast.MovieCastResponse
import com.example.sprint16_architecture.core.data.dto.details.MovieDetailsRequest
import com.example.sprint16_architecture.core.data.dto.details.MovieDetailsResponse
import com.example.sprint16_architecture.core.data.dto.search.MovieDto
import com.example.sprint16_architecture.core.data.dto.search.MoviesSearchRequest
import com.example.sprint16_architecture.core.data.dto.search.MoviesSearchResponse
import com.example.sprint16_architecture.core.data.network.NetworkClient
import com.example.sprint16_architecture.core.data.network.Resource
import com.example.sprint16_architecture.core.data.storage.db.AppDatabase
import com.example.sprint16_architecture.core.data.storage.prefs.LocalStorage
import com.example.sprint16_architecture.core.domain.api.MoviesRepository
import com.example.sprint16_architecture.core.domain.models.Movie
import com.example.sprint16_architecture.core.domain.models.MovieCast
import com.example.sprint16_architecture.core.domain.models.MovieDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepositoryImpl(
    private val networkClient: NetworkClient,
    private val localStorage: LocalStorage,
    private val movieCastConverter: MovieCastConverter,
    private val appDatabase: AppDatabase,
    private val movieDbConvertor: MovieDbConvertor,
) : MoviesRepository {

    override suspend fun searchMovies(expression: String): Flow<Resource<List<Movie>>> = flow {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))
        emit(when (response.resultCode) {
            200 -> {
                val searchResponse = response as MoviesSearchResponse
                val stored = localStorage.getSavedFavorites()
                saveMovie(searchResponse.results)
                Resource.Success((searchResponse).results.map {
                    Movie(it.id, it.resultType, it.image, it.title, it.description, stored.contains(it.id))
                })
            }
            -1 -> Resource.Error("Проверьте подключение к интернету")
            else -> Resource.Error("Ошибка сервера")
        })
    }
    override fun searchDetails(movieId: String): Resource<MovieDetails> {
        val response = networkClient.doRequest(MovieDetailsRequest(movieId))
        return when (response.resultCode) {
            200 -> {
                with(response as MovieDetailsResponse) {
                    Resource.Success(MovieDetails(id, title, imDbRating, year,
                        countries, genres, directors, writers, stars, plot)
                    )
                }
            }
            -1 -> Resource.Error("Проверьте подключение к интернету")
            else -> Resource.Error("Ошибка сервера")
        }
    }

    override fun searchMovieCast(movieId: String): Resource<MovieCast> {
        val response = networkClient.doRequest(MovieCastRequest(movieId))
        return when (response.resultCode) {
            200 -> Resource.Success(data = movieCastConverter.convert(response as MovieCastResponse))
            -1 -> Resource.Error("Проверьте подключение к интернету")
            else -> Resource.Error("Ошибка сервера")
        }
    }


    override fun addMovieToFavorites(movie: Movie) {
        localStorage.addToFavorites(movie.id)
    }

    override fun removeMovieFromFavorites(movie: Movie) {
        localStorage.removeFromFavorites(movie.id)
    }

    private suspend fun saveMovie(movie: List<MovieDto>) {
        val movieEntities = movie.map { movieDbConvertor.map(it) }
        appDatabase.movieDao().insertMovies(movieEntities)
    }
}