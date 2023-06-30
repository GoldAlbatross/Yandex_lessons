package com.example.sprint_20_coroutines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val text = MutableLiveData<String>()
    fun textLiveData(): LiveData<String> = text


    init {
        val job = viewModelScope.launch(Dispatchers.Default) { calculateFactorial(value = 12) }

    }

    private suspend fun calculateFactorial(value: Int) {
        var result = 1
        (1..value).forEach { item ->
            result *= item
            text.postValue(result.toString())
            delay(timeMillis = 1000L)
            //if ()
        }
    }

}