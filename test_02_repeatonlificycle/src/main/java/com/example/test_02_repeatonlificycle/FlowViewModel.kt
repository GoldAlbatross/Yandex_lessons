package com.example.test_02_repeatonlificycle

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.LinkedBlockingQueue

class FlowViewModel : ViewModel(), DefaultLifecycleObserver {

    private val stateQueue = LinkedBlockingQueue<Int>(100)

    val state: Flow<Int> = flow {
        while (true) {
            val nextValue = stateQueue.take()
            Log.d("qqq", "viewModel -> nextValue: $nextValue")
            delay(1000)
            emit(nextValue)
        }
    }.flowOn(Dispatchers.IO)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            var count = 0
            while (count < 1000) {
                stateQueue.put(count++)
            }
        }
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        // Очищаем очередь при уходе в onPause, чтобы не сохранять старые значения
        stateQueue.clear()
    }
}