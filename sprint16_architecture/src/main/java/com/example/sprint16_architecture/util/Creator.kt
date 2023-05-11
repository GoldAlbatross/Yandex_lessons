package com.example.sprint16_architecture.util

import android.content.Context
import com.example.sprint16_architecture.data.network.MoviesRepositoryImpl
import com.example.sprint16_architecture.data.network.RetrofitNetworkClient
import com.example.sprint16_architecture.domain.api.MoviesInteractor
import com.example.sprint16_architecture.domain.api.MoviesRepository
import com.example.sprint16_architecture.domain.impl.MoviesInteractorImpl
import com.example.sprint16_architecture.presentation.movies.MoviesSearchPresenter
import com.example.sprint16_architecture.presentation.poster.PosterPresenter
import com.example.sprint16_architecture.presentation.poster.PosterView

object Creator {

    private fun getMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepositoryImpl(networkClient = RetrofitNetworkClient(context = context))
    }

    fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(repository = getMoviesRepository(context = context))
    }

    fun provideMoviesSearchPresenter(context: Context, ): MoviesSearchPresenter {
        return MoviesSearchPresenter(context = context,)
    }

    fun providePosterPresenter(view: PosterView, imageUrl:String): PosterPresenter {
        return PosterPresenter(view = view, imageUrl = imageUrl)
    }
}