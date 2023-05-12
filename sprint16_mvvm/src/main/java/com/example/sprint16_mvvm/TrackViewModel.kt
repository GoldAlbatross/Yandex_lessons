package com.example.sprint16_mvvm
//
//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
//import androidx.lifecycle.viewmodel.CreationExtras
//import androidx.lifecycle.viewmodel.initializer
//import androidx.lifecycle.viewmodel.viewModelFactory
//
//class TrackViewModel(
//    private val trackId: Int,
//    private val tracksInteractor: TracksInteractor,
//) : ViewModel() {
//
//    private var screenStateLiveData = MutableLiveData<TrackScreenState>(TrackScreenState.Loading)
//    private val playStatusLiveData = MutableLiveData<PlayStatus>()
//
//    init {
//        tracksInteractor.loadSomeData(
//            trackId = trackId,
//            onComplete = { trackModel ->
//                // безопасно с любого потока
//                screenStateLiveData.postValue(TrackScreenState.Content(trackModel))
//            }
//        )
//    }
//    fun getScreenStateLiveData(): LiveData<TrackScreenState> = screenStateLiveData
//    fun getPlayStatusLiveData(): LiveData<PlayStatus> = playStatusLiveData
//
//    fun play() {
//        trackPlayer.play(
//            trackId = trackId,
//            // 1
//            statusObserver = object : TrackPlayer.StatusObserver {
//                override fun onProgress(progress: Float) {
//                    // 2
//                    playStatusLiveData.value = getCurrentPlayStatus().copy(progress = progress)
//                }
//
//                override fun onStop() {
//                    // 3
//                    playStatusLiveData.value = getCurrentPlayStatus().copy(isPlaying = false)
//                }
//
//                override fun onPlay() {
//                    // 4
//                    playStatusLiveData.value = getCurrentPlayStatus().copy(isPlaying = true)
//                }
//            },
//        )
//    }
//
//    fun pause() {
//        trackPlayer.pause(trackId)
//    }
//
//    // 6
//    override fun onCleared() {
//        trackPlayer.release(trackId)
//    }
//
//    private fun getCurrentPlayStatus(): PlayStatus {
//        return playStatusLiveData.value ?: PlayStatus(progress = 0f, isPlaying = false)
//    }
//
//    companion object {
//        fun getViewModelFactory(trackId: Int): ViewModelProvider.Factory =
//            viewModelFactory {
//                initializer {
//                    val interactor = (this[APPLICATION_KEY] as App).provideTracksInteractor()
//                    TrackViewModel(trackId, interactor)
//                }
//            }
//    }
//}
//
//
//
//
//
////рабочая версия с большим количеством кода
////    companion object {
////        fun getViewModelFactory(trackId: Int): ViewModelProvider.Factory =
////            object : ViewModelProvider.Factory {
////
////                @Suppress("UNCHECKED_CAST")
////                override fun <T : ViewModel> create(
////                    modelClass: Class<T>,
////                    extras: CreationExtras,
////                ): T {
////                    val application = checkNotNull(extras[APPLICATION_KEY])
////                    return TrackViewModel(
////                        trackId,
////                        (application as App).provideTracksInteractor(),
////                    ) as T
////                }
////            }
////    }