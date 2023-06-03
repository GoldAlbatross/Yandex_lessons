package com.example.sprint16_architecture.presentation.poster

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sprint16_architecture.domain.api.MoviesInteractor
import com.example.sprint16_architecture.domain.models.MovieDetails
import com.example.sprint16_architecture.domain.models.SearchType
import com.example.sprint16_architecture.presentation.model.AboutState

class AboutViewModel(
    movieId: String,
    moviesInteractor: MoviesInteractor,
) : ViewModel() {

    private val stateLiveData = MutableLiveData<AboutState>()
    fun observeState(): LiveData<AboutState> = stateLiveData

    init {
        Log.d("qqq","+++$movieId")
        moviesInteractor.getDataFromApi(movieId, SearchType.DETAILS, object : MoviesInteractor.Consumer {
            override fun <T> consume(data: T?, errorMessage: String?) {
                if (data != null) {
                    stateLiveData.postValue(AboutState.Content(data as MovieDetails))
                } else {
                    stateLiveData.postValue(AboutState.Error(errorMessage ?: "Unknown error"))
                }
            }
        })
    }
}