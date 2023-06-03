package com.example.sprint16_architecture.di

import com.example.sprint16_architecture.data.MoviesRepositoryImpl
import com.example.sprint16_architecture.domain.api.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MoviesRepository> {
        MoviesRepositoryImpl(get(), get())
    }
}