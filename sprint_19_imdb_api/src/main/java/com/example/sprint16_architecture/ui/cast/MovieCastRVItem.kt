package com.example.sprint16_architecture.ui.cast

import com.example.sprint16_architecture.domain.models.MovieCastPerson

sealed interface MovieCastRVItem {

    data class HeaderItem(val headerText: String): MovieCastRVItem

    data class PersonItem(val data: MovieCastPerson): MovieCastRVItem

}