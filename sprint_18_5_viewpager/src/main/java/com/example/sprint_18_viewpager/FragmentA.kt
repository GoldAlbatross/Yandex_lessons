package com.example.sprint_18_viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.sprint_18_viewpager.databinding.FragmentABinding

// Родительский класс, в котором будет наш ViewPager
class FragmentA : Fragment(), SelectPage {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentABinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View { return binding.root }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.songText.text = arguments?.getString(SONG_NAME_KEY).plus(other = " | A")

        // Таким образом происходит установка адаптера нашему ViewPager
        val adapter = PagerAdapter(hostFragment = this)
        binding.pager.adapter = adapter
    }

    override fun navigateTo(page: Int) {
        binding.pager.currentItem = page
    }

    companion object {
        const val SONG_NAME_KEY = "SONG_NAME_KEY"
        fun getInstance(songName: String): FragmentA = FragmentA().apply {
            arguments = bundleOf(SONG_NAME_KEY to songName)
        }
    }

}