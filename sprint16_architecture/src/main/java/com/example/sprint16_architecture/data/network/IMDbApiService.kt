package com.example.sprint16_architecture.data.network

import com.example.sprint16_architecture.data.dto.MoviesSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IMDbApiService {
    @GET("/en/API/SearchMovie/k_3ph8h7kw/{expression}")
    fun searchMovies(@Path("expression") expression: String): Call<MoviesSearchResponse>
}