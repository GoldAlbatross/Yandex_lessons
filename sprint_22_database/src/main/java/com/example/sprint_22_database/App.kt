package com.example.sprint_22_database

import android.app.Application
import com.markodevcic.peko.PermissionRequester

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        PermissionRequester.initialize(applicationContext)
    }

    companion object {
        val requester = PermissionRequester.instance()
    }

}