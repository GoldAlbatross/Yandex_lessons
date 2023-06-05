package com.example.sprint16_architecture.ui.poster.activity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.sprint16_architecture.databinding.FragmentAboutBinding
import com.example.sprint16_architecture.domain.models.MovieDetails
import com.example.sprint16_architecture.ui.cast.MoviesCastActivity
import com.example.sprint16_architecture.ui.poster.model.AboutState
import com.example.sprint16_architecture.ui.poster.view_model.AboutViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AboutFragment : Fragment() {


    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentAboutBinding.inflate(layoutInflater) }
    private val aboutViewModel: AboutViewModel by viewModel {
        parametersOf(requireArguments().getString(MOVIE_ID))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View { return binding.root }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        aboutViewModel.observeState().observe(viewLifecycleOwner) {
            when(it) {
                is AboutState.Content -> showDetails(it.movie)
                is AboutState.Error -> showErrorMessage(it.message)
            }
        }

        binding.castBtn.setOnClickListener {
            startActivity(MoviesCastActivity.newInstance(
                context = requireContext(),
                movieId = arguments?.getString(MOVIE_ID).orEmpty(),
            ))
        }
    }

    private fun showErrorMessage(message: String) {
        binding.apply {
            details.visibility = View.GONE
            errorMessage.visibility = View.VISIBLE
            errorMessage.text = message
        }
    }

    private fun showDetails(movieDetails: MovieDetails) {
        binding.apply {
            details.visibility = View.VISIBLE
            errorMessage.visibility = View.GONE
            title.text = movieDetails.title
            ratingValue.text = movieDetails.imDbRating
            yearValue.text = movieDetails.year
            countryValue.text = movieDetails.countries
            genreValue.text = movieDetails.genres
            directorValue.text = movieDetails.directors
            writerValue.text = movieDetails.writers
            castValue.text = movieDetails.stars
            plot.text = movieDetails.plot
        }

    }

    companion object {
        private const val MOVIE_ID = "movie_id"
        fun newInstance(movieId: String) = AboutFragment().apply {
            arguments = bundleOf(MOVIE_ID to movieId)
        }
    }
}