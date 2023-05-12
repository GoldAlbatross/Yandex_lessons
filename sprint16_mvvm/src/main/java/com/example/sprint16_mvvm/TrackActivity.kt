package com.example.sprint16_mvvm
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.core.view.isVisible
//import com.example.sprint16_mvvm.databinding.ActivityTrackBinding
//
//class TrackActivity : ComponentActivity() {
//
//    //private val viewModel by viewModels<TrackViewModel> { TrackViewModel.getViewModelFactory(123) }
//    private lateinit var binding: ActivityTrackBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityTrackBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
////        viewModel.getScreenStateLiveData().observe(this) { screenState ->
////            // 1
////            when (screenState) {
////                is TrackScreenState.Content -> {
////                    changeContentVisibility(loading = false)
////                    binding.picture.setImage(screenState.trackModel.pictureUrl)
////                    binding.author.text = screenState.trackModel.author
////                    binding.trackName.text = screenState.trackModel.name
////                }
////                is TrackScreenState.Loading -> {
////                    changeContentVisibility(loading = true)
////                }
////            }
////        }
////        viewModel.getPlayStatusLiveData().observe(this) { playStatus ->
////            changeButtonStyle(playStatus)
////            binding.seekBar.value = playStatus.progress
////        }
//
//    }
//
//    private fun changeContentVisibility(loading: Boolean) {
//        binding.progressBar.isVisible = loading
//
////        binding.picture.isVisible = !loading
////        binding.author.isVisible = !loading
////        binding.trackName.isVisible = !loading
//    }
////    private fun changeButtonStyle(playStatus: PlayStatus) {
////        // Меняем вид кнопки проигрывания в зависимости от того, играет сейчас трек или нет
////    }
//
//}
//
///*
//
// */