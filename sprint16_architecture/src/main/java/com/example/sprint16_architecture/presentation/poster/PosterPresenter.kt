package com.example.sprint16_architecture.presentation.poster


import android.widget.ImageView

class PosterPresenter(
    private val view: PosterView,
    private val imageUrl: String,
) {

    init {

    }
    private lateinit var poster: ImageView

    fun onCreate() {
        view.setupPosterImage(imageUrl)
    }
}