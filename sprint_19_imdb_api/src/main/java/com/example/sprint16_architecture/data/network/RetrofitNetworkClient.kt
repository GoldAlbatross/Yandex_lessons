package com.example.sprint16_architecture.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.sprint16_architecture.data.dto.details.MovieDetailsRequest
import com.example.sprint16_architecture.data.dto.search.MoviesSearchRequest
import com.example.sprint16_architecture.data.dto.Response
import com.example.sprint16_architecture.data.dto.cast.MovieCastRequest

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