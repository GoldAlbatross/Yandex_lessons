package com.example.sprint_18_viewpager

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sprint_18_viewpager.databinding.FragmentBNestedBinding

class NestedFragmentB: Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentBNestedBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View { return binding.root }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Установка названия песни и передача данных Activity
//        binding.songNestedB.text = (requireActivity() as SongNameProvider).getSongName()
//            .plus(other = " | B")

        // Заставляем наш ViewPager переключиться на следующую страницу
        binding.button.setOnClickListener {
            (parentFragment as? SelectPage)?.navigateTo(page = 0)
        }
    }
}