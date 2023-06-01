package com.example.sprint16_architecture.presentation.poster

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.sprint16_architecture.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PosterActivity : AppCompatActivity() {

    private val poster: ImageView by lazy { findViewById(R.id.poster) }
    private val viewModel by viewModel<PosterViewModel> {
        parametersOf(this, url)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poster)

        val url = intent.extras?.getString("poster", "") ?: ""
        viewModel.onCreate()
    }

}