package com.example.sprint16_architecture.core.ui.cast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sprint16_architecture.databinding.FragmentMoviesCastBinding
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MovieCastFragment: Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentMoviesCastBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModel<MovieCastViewModel>{
        parametersOf(arguments?.getString(KEY_MOVIE_ID) ?: "")
    }
    private val adapter = ListDelegationAdapter(
        movieCastHeaderDelegate(),
        movieCastPersonDelegate(),
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View { return binding.root }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            moviesCastRecyclerView.adapter = adapter
            moviesCastRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.observeState().observe(viewLifecycleOwner) {
            when (it) {
                is MoviesCastState.Content -> showContent(it)
                is MoviesCastState.Error -> showError(it)
                is MoviesCastState.Loading -> showLoading()
            }
        }
    }

    private fun showLoading() {
        binding.contentContainer.isVisible = false
        binding.errorMessageTextView.isVisible = false
        binding.progressBar.isVisible = true
    }

    private fun showError(state: MoviesCastState.Error) {
        binding.contentContainer.isVisible = false
        binding.progressBar.isVisible = false
        binding.errorMessageTextView.isVisible = true
        binding.errorMessageTextView.text = state.message
    }

    private fun showContent(state: MoviesCastState.Content) {
        binding.progressBar.isVisible = false
        binding.errorMessageTextView.isVisible = false
        binding.contentContainer.isVisible = true
        binding.movieTitle.text = state.fullTitle
        adapter.items = state.items
        adapter.notifyDataSetChanged()
    }

    companion object {
        private const val KEY_MOVIE_ID = "movie_id"
        fun newInstance(movieId: String): Fragment {
            return MovieCastFragment().apply {
                arguments = bundleOf(KEY_MOVIE_ID to movieId)
            }
        }
    }
}