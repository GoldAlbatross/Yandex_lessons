package com.example.sprint_19_navcomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.sprint_19_navcomponent.databinding.ActivityHostBinding

class HostActivity : AppCompatActivity() {

    private val binding by lazy { ActivityHostBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.container_view) as NavHostFragment
//        val navController = navHostFragment.navController
    }
}