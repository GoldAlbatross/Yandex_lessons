package com.example.sprint16_architecture.di

import com.example.sprint16_architecture.domain.api.MoviesInteractor
import com.example.sprint16_architecture.domain.impl.MoviesInteractorImpl
import org.koin.dsl.module

val interactorModule = module {
    
    single<MoviesInteractor> {
        MoviesInteractorImpl(get())
    }
}