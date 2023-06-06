package com.example.sprint16_architecture.di

import com.example.sprint16_architecture.core.navigation.Router
import com.example.sprint16_architecture.core.navigation.RouterImpl
import org.koin.dsl.module

val navigationModule = module {
    val router = RouterImpl()

    single<Router> { router }
    single { router.navigatorHolder }
}