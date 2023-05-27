package com.example.sprint18_fragments4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.sprint18_fragments4.FragmentA.Companion.SONG_NAME_KEY

class MainActivity : AppCompatActivity(), SongNameProvider {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add<FragmentA>(
                    containerViewId = R.id.fragment_container,
                    tag = null,
                    args = bundleOf(SONG_NAME_KEY to getSongName())
                )
            }
        }
    }

    override fun getSongName(): String = "Muse - Starlight"
}