package com.example.sprint16_architecture.core.ui.cast

import com.example.sprint16_architecture.core.domain.models.MovieCastPerson
import com.example.sprint16_architecture.core.ui.Cell

sealed interface MovieCastRVItem: Cell {

    data class HeaderItem(val headerText: String): MovieCastRVItem

    data class PersonItem(val data: MovieCastPerson): MovieCastRVItem

}