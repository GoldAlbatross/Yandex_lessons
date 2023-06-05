package com.example.sprint16_architecture.ui.poster.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PosterViewModel(
    posterUrl: String
) : ViewModel() {

    private val urlLiveData = MutableLiveData(posterUrl)
    fun observeUrl(): LiveData<String> = urlLiveData
}