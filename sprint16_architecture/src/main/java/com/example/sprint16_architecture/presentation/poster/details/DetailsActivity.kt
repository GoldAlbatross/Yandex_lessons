package com.example.sprint16_architecture.presentation.poster.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sprint16_architecture.R
import com.example.sprint16_architecture.databinding.ActivityDetailsBinding
import com.example.sprint16_architecture.presentation.poster.fragment.DetailsViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class DetailsActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailsBinding.inflate(layoutInflater) }
    private lateinit var tabMediator: TabLayoutMediator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val poster = intent.getStringExtra("poster") ?: ""
        val movieId = intent.getStringExtra("id") ?: ""

        binding.viewPager.adapter =
            DetailsViewPagerAdapter(supportFragmentManager, lifecycle, poster, movieId)

        tabMediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when(position) {
                0 -> tab.text = getString(R.string.poster)
                1 -> tab.text = getString(R.string.details)
            }
        }
        tabMediator.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        tabMediator.detach()
    }

}