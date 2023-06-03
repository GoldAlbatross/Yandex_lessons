package com.example.sprint16_architecture.di

import com.example.sprint16_architecture.App
import com.example.sprint16_architecture.presentation.movies.MoviesSearchViewModel
import com.example.sprint16_architecture.presentation.poster.AboutViewModel
import com.example.sprint16_architecture.presentation.poster.PosterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    
    viewModel {
        MoviesSearchViewModel(androidContext() as App, get())
    }

    viewModel {(movieId: String) ->
        AboutViewModel(movieId, get())
    }

    viewModel {(posterUrl: String) ->
        PosterViewModel(posterUrl)
    }
}