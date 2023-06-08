package com.example.sprint16_architecture.core.ui.cast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sprint16_architecture.core.domain.api.MoviesInteractor
import com.example.sprint16_architecture.core.domain.models.MovieCast
import com.example.sprint16_architecture.core.domain.models.SearchType

class MovieCastViewModel(
    movieId: String,
    moviesInteractor: MoviesInteractor,
) : ViewModel() {


    private val stateLiveData = MutableLiveData<MoviesCastState>()
    fun observeState(): LiveData<MoviesCastState> = stateLiveData

    init {
        stateLiveData.postValue(MoviesCastState.Loading)
        moviesInteractor.getDataFromApi(movieId, SearchType.CAST, object : MoviesInteractor.Consumer {

            override fun <T> consume(data: T?, errorMessage: String?) {
                if (data != null) stateLiveData.postValue(castToUiStateContent(data as MovieCast))
                else stateLiveData.postValue(MoviesCastState.Error(errorMessage ?: "Unknown error"))
            }
        })
    }

    private fun castToUiStateContent(cast: MovieCast): MoviesCastState {
        // Строим список элементов RecyclerView
        val items = buildList<MovieCastRVItem> {
            // Если есть хотя бы один режиссёр, добавим заголовок
            if (cast.directors.isNotEmpty()) {
                this += MovieCastRVItem.HeaderItem("Directors")
                this += cast.directors.map { MovieCastRVItem.PersonItem(it) }
            }

            // Если есть хотя бы один сценарист, добавим заголовок
            if (cast.writers.isNotEmpty()) {
                this += MovieCastRVItem.HeaderItem("Writers")
                this += cast.writers.map { MovieCastRVItem.PersonItem(it) }
            }

            // Если есть хотя бы один актёр, добавим заголовок
            if (cast.actors.isNotEmpty()) {
                this += MovieCastRVItem.HeaderItem("Actors")
                this += cast.actors.map { MovieCastRVItem.PersonItem(it) }
            }

            // Если есть хотя бы один дополнительный участник, добавим заголовок
            if (cast.others.isNotEmpty()) {
                this += MovieCastRVItem.HeaderItem("Others")
                this += cast.others.map { MovieCastRVItem.PersonItem(it) }
            }
        }

        return MoviesCastState.Content(
            fullTitle = cast.fullTitle,
            items = items
        )
    }
}