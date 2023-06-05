package com.example.sprint16_architecture.di

import com.example.sprint16_architecture.application.App
import com.example.sprint16_architecture.ui.cast.MovieCastViewModel
import com.example.sprint16_architecture.ui.movies.view_model.MoviesSearchViewModel
import com.example.sprint16_architecture.ui.poster.view_model.AboutViewModel
import com.example.sprint16_architecture.ui.poster.view_model.PosterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    
    viewModel {
        MoviesSearchViewModel(
            application = androidContext() as App,
            moviesInteractor = get(),
        )
    }

    viewModel {(movieId: String) ->
        AboutViewModel(
            movieId = movieId,
            moviesInteractor = get(),
        )
    }

    viewModel {(posterUrl: String) ->
        PosterViewModel(
            posterUrl = posterUrl,
        )
    }

    viewModel { (movieId: String) ->
        MovieCastViewModel(
            movieId = movieId,
            moviesInteractor = get(),
        )
    }
}