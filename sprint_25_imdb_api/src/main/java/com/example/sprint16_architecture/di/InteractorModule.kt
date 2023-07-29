package com.example.sprint16_architecture.di

import com.example.sprint16_architecture.core.domain.api.HistoryInteractor
import com.example.sprint16_architecture.core.domain.api.MoviesInteractor
import com.example.sprint16_architecture.core.domain.api.NamesInteractor
import com.example.sprint16_architecture.core.domain.impl.HistoryInteractorImpl
import com.example.sprint16_architecture.core.domain.impl.MoviesInteractorImpl
import com.example.sprint16_architecture.core.domain.impl.NamesInteractorImpl
import org.koin.dsl.module

val interactorModule = module {
    
    single<MoviesInteractor> {
        MoviesInteractorImpl(repository = get())
    }

    single<NamesInteractor> {
        NamesInteractorImpl(repository = get())
    }

    single<HistoryInteractor> {
        HistoryInteractorImpl(repository = get())
    }
}