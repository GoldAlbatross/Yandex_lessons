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

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)!!
        val tabLayout = activity?.findViewById<TabLayout>(R.id.tabLayout)!!
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
        const val TAG = "details_fragment"

        fun newInstance(movieId: String, posterUrl: String): Fragment {
            return DetailsFragment().apply {
                // Пробрасываем аргументы в Bundle
                arguments = bundleOf(
                    MOVIE_ID_KEY to movieId,
                    POSTER_URL_KEY to posterUrl
                )
            }
        }
    }
}