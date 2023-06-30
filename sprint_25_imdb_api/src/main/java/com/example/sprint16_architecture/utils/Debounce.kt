package com.example.sprint16_architecture.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun <T> debounce(
    delayMillis: Long,
    coroutineScope: CoroutineScope,
    useLastParam: Boolean,
    action: (T) -> Unit
): (T) -> Unit {

    var job: Job? = null
    return { param: T ->
        if (useLastParam) { job?.cancel() }
        if (job?.isCompleted != false || useLastParam) {
            job = coroutineScope.launch {
                delay(delayMillis)
                action(param)
            }
        }
    }
}