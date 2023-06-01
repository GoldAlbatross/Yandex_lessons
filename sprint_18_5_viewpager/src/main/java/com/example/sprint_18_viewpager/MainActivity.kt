package com.example.sprint_18_viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

// Главная активити, к которой будут привязаны наши фрагменты.
class MainActivity : AppCompatActivity(), SongNameProvider {

    override fun getSongName(): String = "Jungle - Casio"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            // Отображаем родительский фрагмент, в который дальше добавим ViewPager с фрагментами.
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, FragmentA.getInstance(getSongName()))
                .commit()
        }
    }
}