package com.example.sprint16_architecture.di

import com.example.sprint16_architecture.application.App
import com.example.sprint16_architecture.core.ui.cast.MovieCastViewModel
import com.example.sprint16_architecture.core.ui.movies.MoviesSearchViewModel
import com.example.sprint16_architecture.core.ui.details.AboutViewModel
import com.example.sprint16_architecture.core.ui.details.PosterViewModel
import com.example.sprint16_architecture.core.ui.history.HistoryViewModel
import com.example.sprint16_architecture.core.ui.name.NamesViewModel
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
        PosterViewModel(posterUrl = posterUrl,)
    }

    viewModel { (movieId: String) ->
        MovieCastViewModel(
            movieId = movieId,
            moviesInteractor = get(),
        )
    }

    viewModel {
        NamesViewModel(
            appContext = androidContext() as App,
            namesInteractor = get(),
        )
    }

    viewModel {
        HistoryViewModel(
            historyInteractor = get()
        )
    }
}