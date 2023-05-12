package com.example.sprint16_mvvm

sealed class TrackScreenState {
    object Loading: TrackScreenState()
    data class Content(val trackModel: TrackModel): TrackScreenState()
}
