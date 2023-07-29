package com.example.sprint16_architecture.core.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sprint16_architecture.core.domain.api.HistoryInteractor
import com.example.sprint16_architecture.core.domain.models.Movie
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val historyInteractor: HistoryInteractor,
) : ViewModel() {

    private val stateLiveData = MutableLiveData<HistoryState>()

    fun observeState(): LiveData<HistoryState> = stateLiveData

    fun fillData() {
        renderState(HistoryState.Loading)
        viewModelScope.launch {
            historyInteractor.historyMovies()
                .collect { movies -> processResult(movies) }
        }
    }

    private fun processResult(movies: List<Movie>) {
        if (movies.isEmpty()) {
            renderState(HistoryState.Empty("nothing_searched_yet"))
        } else {
            renderState(HistoryState.Content(movies))
        }
    }

    private fun renderState(state: HistoryState) {
        stateLiveData.postValue(state)
    }
}