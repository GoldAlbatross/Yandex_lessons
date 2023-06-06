package com.example.sprint16_architecture.core.ui.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.sprint16_architecture.R
import com.example.sprint16_architecture.core.ui.movies.MoviesFragment

class RootActivity: AppCompatActivity(R.layout.activity_root) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add(R.id.rootFragmentContainerView, MoviesFragment())
                setReorderingAllowed(true)
            }
        }
    }
}