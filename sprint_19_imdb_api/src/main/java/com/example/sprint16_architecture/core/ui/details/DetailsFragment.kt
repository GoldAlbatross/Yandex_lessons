package com.example.sprint16_architecture.core.ui.details

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.sprint16_architecture.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailsFragment: Fragment(R.layout.fragment_details) {

    private lateinit var tabMediator: TabLayoutMediator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val poster = arguments?.getString(POSTER_URL_KEY) ?: ""
        val movieId = arguments?.getString(MOVIE_ID_KEY) ?: ""

        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)!!
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)!!
        viewPager.adapter = DetailsViewPagerAdapter(
                fragmentManager = childFragmentManager,
                lifecycle = lifecycle,
                posterUrl = poster,
                movieId = movieId,
            )

        tabMediator = TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when(position) {
                0 -> tab.text = getString(R.string.poster)
                1 -> tab.text = getString(R.string.details)
            }
        }
        tabMediator.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tabMediator.detach()
    }

    companion object {
        private const val MOVIE_ID_KEY = "movie_id"
        private const val POSTER_URL_KEY = "poster_url"
        fun createArgs(id: String, image: String): Bundle =
            bundleOf(MOVIE_ID_KEY to id,POSTER_URL_KEY to image)
    }
}