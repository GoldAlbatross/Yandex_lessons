package com.example.sprint16_architecture.core.ui.movies.model

sealed interface ToastState {
    object None: ToastState
    data class Show(val additionalMessage: String): ToastState
}