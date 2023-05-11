package com.example.sprint16_architecture.presentation.movies

import com.example.sprint16_architecture.domain.models.Movie
import com.example.sprint16_architecture.util.MoviesState
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface MoviesView: MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun render(state: MoviesState)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(message: String)
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showLoading()
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showError(errorMessage: String)
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showEmpty(emptyMessage: String)
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showContent(movies: List<Movie>)

}