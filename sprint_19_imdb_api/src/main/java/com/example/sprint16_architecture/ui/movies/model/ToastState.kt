package com.example.sprint16_architecture.ui.movies.model

sealed interface ToastState {
    object None: ToastState
    data class Show(val additionalMessage: String): ToastState
}