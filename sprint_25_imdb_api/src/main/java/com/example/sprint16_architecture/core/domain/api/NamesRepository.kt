package com.example.sprint16_architecture.core.domain.api

import com.example.sprint16_architecture.core.data.network.Resource
import com.example.sprint16_architecture.core.domain.models.Person
import kotlinx.coroutines.flow.Flow

interface NamesRepository {

    fun searchNames(expression: String): Flow<Resource<List<Person>>>
}