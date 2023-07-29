package com.example.sprint16_architecture.core.data.network

import com.example.sprint16_architecture.core.data.dto.cast.MovieCastResponse
import com.example.sprint16_architecture.core.data.dto.details.MovieDetailsResponse
import com.example.sprint16_architecture.core.data.dto.name_search.NamesSearchResponse
import com.example.sprint16_architecture.core.data.dto.search.MoviesSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IMDbApiService {
    @GET("/en/API/SearchMovie/k_3ph8h7kw/{expression}")
    fun searchMovies(@Path("expression") expression: String): Call<MoviesSearchResponse>
    @GET("/en/API/Title/k_3ph8h7kw/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: String): Call<MovieDetailsResponse>
    @GET("/en/API/FullCast/k_3ph8h7kw/{cast}")
    fun getMovieCast(@Path("cast") cast: String): Call<MovieCastResponse>


    @GET("/en/API/SearchName/k_3ph8h7kw/{expression}")
    suspend fun searchNames(@Path("expression") expression: String): NamesSearchResponse
}