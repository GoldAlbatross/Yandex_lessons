package com.example.sprint18_fragments4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.sprint18_fragments4.databinding.FragmentABinding

class FragmentA : Fragment() {

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

        binding.song.text = arguments?.getString(SONG_NAME_KEY)
            .plus(other = " | A")

        childFragmentManager.commit {
            add<NestedFragmentA>(R.id.fragment_child_container)
        }
    }

    companion object {
        const val SONG_NAME_KEY = "SONG_NAME_KEY"
        fun getInstance(songName: String): FragmentA = FragmentA().apply {
            arguments = bundleOf(SONG_NAME_KEY to songName)
        }
    }
}