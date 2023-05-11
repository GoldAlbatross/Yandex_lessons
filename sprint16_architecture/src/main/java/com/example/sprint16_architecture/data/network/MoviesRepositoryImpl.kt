package com.example.sprint16_architecture.data.network

import com.example.sprint16_architecture.data.NetworkClient
import com.example.sprint16_architecture.data.dto.MoviesSearchRequest
import com.example.sprint16_architecture.data.dto.MoviesSearchResponse
import com.example.sprint16_architecture.domain.api.MoviesRepository
import com.example.sprint16_architecture.domain.models.Movie
import com.example.sprint16_architecture.util.Resource

class MoviesRepositoryImpl(private val networkClient: NetworkClient) : MoviesRepository {

    override fun searchMovies(expression: String): Resource<List<Movie>> {

        val response = networkClient.doRequest(MoviesSearchRequest(expression))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                Resource.Success((response as MoviesSearchResponse).results.map {
                    Movie(it.id, it.resultType, it.image, it.title, it.description)})
            }
            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }
}