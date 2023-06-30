package com.example.sprint16_architecture.core.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.sprint16_architecture.core.data.dto.Response
import com.example.sprint16_architecture.core.data.dto.cast.MovieCastRequest
import com.example.sprint16_architecture.core.data.dto.details.MovieDetailsRequest
import com.example.sprint16_architecture.core.data.dto.name_search.NamesSearchRequest
import com.example.sprint16_architecture.core.data.dto.name_search.NamesSearchResponse
import com.example.sprint16_architecture.core.data.dto.search.MoviesSearchRequest

class RetrofitNetworkClient(
    private val imdbService: IMDbApiService,
    private val context: Context
    ) : NetworkClient {


    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }

    override fun doRequest(dto: Any): Response {
        if (!isConnected())
            return Response().apply { resultCode = -1 }

        val response = when (dto) {
            is NamesSearchRequest -> imdbService.searchNames(dto.expression).execute()
            is MoviesSearchRequest -> imdbService.searchMovies(dto.expression).execute()
            is MovieDetailsRequest -> imdbService.getMovieDetails(dto.movieId).execute()
            is MovieCastRequest -> imdbService.getMovieCast(dto.movieId).execute()
            else -> return Response().apply { resultCode = 400 }
        }

        val body = response.body()
        return body?.apply { resultCode = response.code() }
            ?: Response().apply { resultCode = response.code() }
    }

}