package com.example.sprint16_architecture.core.ui.name

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sprint16_architecture.R
import com.example.sprint16_architecture.core.domain.api.NamesInteractor
import com.example.sprint16_architecture.core.domain.models.Person
import com.example.sprint16_architecture.core.ui.SingleLiveEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NamesViewModel(
    private val appContext: Application,
    private val namesInteractor: NamesInteractor
) : AndroidViewModel(appContext) {

    private val stateLiveData = MutableLiveData<NamesState>()
    fun observeState(): LiveData<NamesState> = stateLiveData

    private val showToast = SingleLiveEvent<String?>()
    fun observeShowToast(): LiveData<String?> = showToast

    private var latestSearchText: String? = null

    private var job: Job? = null

    fun searchDebounce(changedText: String) {
        if (latestSearchText == changedText) return

        latestSearchText = changedText
        job?.cancel()
        job = viewModelScope.launch {
            delay(SEARCH_DEBOUNCE_DELAY)
            searchRequest(changedText)
        }
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            renderState(NamesState.Loading)

            viewModelScope.launch {
                namesInteractor.searchNames(newSearchText).collect { pair ->
                    when {
                        pair.first == null -> { renderState(
                            NamesState.Error(message = appContext.getString(R.string.something_went_wrong)))
                            //showToast.postValue(pair.second)
                            showToast.value = pair.second
                        }
                        pair.first!!.isEmpty() -> { renderState(
                            NamesState.Empty(message = appContext.getString(R.string.nothing_found)))
                        }
                        else -> { renderState(
                            NamesState.Content(persons = pair.first!!))
                        }
                    }
                }
            }
        }
    }
    private fun renderState(state: NamesState) {
        stateLiveData.postValue(state)
    }
    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
    }
}