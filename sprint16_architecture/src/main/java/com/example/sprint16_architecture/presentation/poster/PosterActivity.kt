package com.example.sprint16_architecture.presentation.poster

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.sprint16_architecture.R
import com.example.sprint16_architecture.util.Creator

class PosterActivity : Activity(), PosterView {

    private lateinit var posterPresenter: PosterPresenter
    private val poster: ImageView by lazy { findViewById(R.id.poster) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val url = intent.extras?.getString("poster", "") ?: ""
        posterPresenter = Creator.providePosterPresenter(this, url)
        posterPresenter.onCreate()
        setContentView(R.layout.activity_poster)
    }

    override fun setupPosterImage(url: String) {
        Glide.with(applicationContext)
            .load(url)
            .into(poster)
    }
}