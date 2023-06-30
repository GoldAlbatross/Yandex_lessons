package com.example.sprint16_architecture.application

import android.app.Application
import com.example.sprint16_architecture.di.dataModule
import com.example.sprint16_architecture.di.interactorModule
import com.example.sprint16_architecture.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(dataModule, interactorModule, viewModelModule)
        }
    }
}