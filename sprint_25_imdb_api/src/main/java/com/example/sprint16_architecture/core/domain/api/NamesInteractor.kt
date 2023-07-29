package com.example.sprint16_architecture.core.domain.api

import com.example.sprint16_architecture.core.domain.models.Person
import kotlinx.coroutines.flow.Flow


interface NamesInteractor {
    fun searchNames(expression: String) : Flow<Pair<List<Person>?, String?>>
}