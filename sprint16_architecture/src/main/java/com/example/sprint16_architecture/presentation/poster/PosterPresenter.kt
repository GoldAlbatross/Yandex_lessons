package com.example.sprint16_architecture.presentation.poster

import android.app.Activity
import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.sprint16_architecture.R
import com.example.sprint16_architecture.presentation.movies.MoviesView

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