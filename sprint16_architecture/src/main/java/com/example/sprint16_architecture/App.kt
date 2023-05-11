package com.example.sprint16_architecture

import android.app.Application
import com.example.sprint16_architecture.presentation.movies.MoviesSearchPresenter

class App: Application() {

    companion object{
        lateinit var instance: App
        private set
    }
    var moviesSearchPresenter : MoviesSearchPresenter? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}