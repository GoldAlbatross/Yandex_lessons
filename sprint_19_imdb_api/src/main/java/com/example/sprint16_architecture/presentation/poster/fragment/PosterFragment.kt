package com.example.sprint16_architecture.presentation.poster.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.sprint16_architecture.databinding.FragmentPosterBinding
import com.example.sprint16_architecture.presentation.poster.PosterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PosterFragment : Fragment() {

    private val posterViewModel: PosterViewModel by viewModel {
        parametersOf(requireArguments().getString(POSTER_URL))
    }

    private lateinit var binding: FragmentPosterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentPosterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        posterViewModel.observeUrl().observe(viewLifecycleOwner) {
            showPoster(it)
        }
    }

    private fun showPoster(url: String) {
        context?.let {
            Glide.with(it)
                .load(url)
                .into(binding.poster)
        }
    }

    companion object {
        private const val POSTER_URL = "poster_url"
        fun newInstance(posterUrl: String) = PosterFragment().apply {
            arguments = bundleOf(POSTER_URL to posterUrl)
        }
    }
}