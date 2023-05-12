package com.example.sprint16_architecture.util

sealed interface ToastState {
    object None: ToastState
    data class Show(val additionalMessage: String): ToastState
}