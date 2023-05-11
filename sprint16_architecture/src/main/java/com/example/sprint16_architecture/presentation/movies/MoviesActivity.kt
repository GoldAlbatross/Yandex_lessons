package com.example.sprint16_architecture.presentation.movies

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sprint16_architecture.App
import com.example.sprint16_architecture.R
import com.example.sprint16_architecture.domain.models.Movie
import com.example.sprint16_architecture.util.Creator
import com.example.sprint16_architecture.presentation.poster.PosterActivity
import com.example.sprint16_architecture.util.MoviesState

class MoviesActivity : Activity(), MoviesView {

    companion object { private const val CLICK_DEBOUNCE_DELAY = 1000L }
    private var presenter = App.instance.moviesSearchPresenter
    private val adapter = MoviesAdapter {
        if (clickDebounce()) {
            val intent = Intent(this, PosterActivity::class.java)
            intent.putExtra("poster", it.image)
            startActivity(intent)
        }
    }
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var queryInput: EditText
    private lateinit var placeholderMessage: TextView
    private lateinit var moviesList: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var isClickAllowed = true
    private var textWatcher: TextWatcher? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        if (presenter == null) {
            presenter = Creator.provideMoviesSearchPresenter(context = this)
            App.instance.moviesSearchPresenter = presenter
        }

        placeholderMessage = findViewById(R.id.placeholderMessage)
        queryInput = findViewById(R.id.queryInput)
        moviesList = findViewById(R.id.locations)
        progressBar = findViewById(R.id.progressBar)

        moviesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        moviesList.adapter = adapter

        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter!!.searchDebounce(
                    changedText = s?.toString() ?: ""
                )
            }
            override fun afterTextChanged(s: Editable?) {}
        }
        textWatcher?.let { queryInput.addTextChangedListener(it) }
    }

    override fun onStart() {
        super.onStart()
        presenter?.attachView(this)
    }

    override fun onResume() {
        super.onResume()
        presenter?.attachView(this)
    }

    override fun onPause() {
        super.onPause()
        presenter?.detachView()
    }

    override fun onStop() {
        super.onStop()
        presenter?.detachView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter?.detachView()
    }
    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) { App.instance.moviesSearchPresenter = null }
        presenter?.detachView()
        presenter?.onDestroy()
        textWatcher?.let { queryInput.removeTextChangedListener(it) }
    }

    private fun clickDebounce() : Boolean {
        val current = isClickAllowed
        if (isClickAllowed) {
            isClickAllowed = false
            handler.postDelayed({ isClickAllowed = true }, CLICK_DEBOUNCE_DELAY)
        }
        return current
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun render(state: MoviesState) {
        when (state) {
            is MoviesState.Loading -> showLoading()
            is MoviesState.Content -> showContent(state.movies)
            is MoviesState.Error -> showError(state.errorMessage)
            is MoviesState.Empty -> showEmpty(state.message)
        }
    }

    override fun showLoading() {
        moviesList.visibility = GONE
        placeholderMessage.visibility = GONE
        progressBar.visibility = VISIBLE
    }

    override fun showError(errorMessage: String) {
        moviesList.visibility = GONE
        placeholderMessage.visibility = VISIBLE
        progressBar.visibility = GONE

        placeholderMessage.text = errorMessage
    }

    override fun showEmpty(emptyMessage: String) {
        showError(emptyMessage)
    }

    override fun showContent(movies: List<Movie>) {
        moviesList.visibility = VISIBLE
        placeholderMessage.visibility = GONE
        progressBar.visibility = GONE

        adapter.movies.clear()
        adapter.movies.addAll(movies)
        adapter.notifyDataSetChanged()
    }
}