package com.example.sprint16_architecture.core.ui.movies

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sprint16_architecture.R
import com.example.sprint16_architecture.core.domain.models.Movie
import com.example.sprint16_architecture.core.ui.details.DetailsFragment
import com.example.sprint16_architecture.core.ui.root.RootActivity
import com.example.sprint16_architecture.databinding.FragmentMoviesBinding
import com.example.sprint16_architecture.utils.debounce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

const val TAG = "qqq"
class MoviesFragment: Fragment() {

    private lateinit var onMovieClickDebounce: (Movie) -> Unit
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentMoviesBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModel<MoviesSearchViewModel>()
    private var textWatcher: TextWatcher? = null
    private var adapter: MoviesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View { return binding.root }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onMovieClickDebounce = debounce(
                delayMillis = CLICK_DEBOUNCE_DELAY,
                coroutineScope = viewLifecycleOwner.lifecycleScope,
                useLastParam = false
            ) { movie -> Log.d(TAG, "onViewCreated: ")
            findNavController().navigate(
                resId = R.id.action_moviesFragment_to_detailsFragment,
                args = DetailsFragment.createArgs(movie.id, movie.image)
            )
        }

        adapter = MoviesAdapter(object : MoviesAdapter.MovieClickListener {
            override fun onMovieClick(movie: Movie) {
                (activity as RootActivity).animateBottomNavigationView()
                onMovieClickDebounce(movie)
            }
            override fun onFavoriteToggleClick(movie: Movie) {
                viewModel.toggleFavorite(movie)
            }
        })

        binding.recycler.adapter = adapter
        binding.recycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.searchDebounce(changedText = s?.toString() ?: "")
            }
            override fun afterTextChanged(s: Editable?) {}
        }
        textWatcher?.let { binding.queryInput.addTextChangedListener(it) }

        //подписки на LiveData
        viewModel.observeViewState().observe(viewLifecycleOwner) { render(it) }
        viewModel.observeToastState().observe(viewLifecycleOwner) { toastState ->
            if(toastState is ToastState.Show) { showToast(toastState.additionalMessage)
                viewModel.toastWasShown()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
        binding.recycler.adapter = null
        textWatcher?.let { binding.queryInput.removeTextChangedListener(it) }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun render(state: MoviesState) {
        when (state) {
            is MoviesState.Loading -> showLoading()
            is MoviesState.Content -> showContent(state.movies)
            is MoviesState.Error -> showError(state.errorMessage)
            is MoviesState.Empty -> showEmpty(state.message)
        }
    }

    private fun showLoading() {
        binding.recycler.visibility = View.GONE
        binding.placeholderMessage.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun showError(errorMessage: String) {
        binding.recycler.visibility = View.GONE
        binding.placeholderMessage.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
        binding.placeholderMessage.text = errorMessage
    }

    private fun showEmpty(emptyMessage: String) {
        showError(emptyMessage)
    }

    private fun showContent(movies: List<Movie>) {
        binding.recycler.visibility = View.VISIBLE
        binding.placeholderMessage.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        adapter?.movies?.clear()
        adapter?.movies?.addAll(movies)
        adapter?.notifyDataSetChanged()
    }

    companion object {
        private const val CLICK_DEBOUNCE_DELAY = 300L
    }

}