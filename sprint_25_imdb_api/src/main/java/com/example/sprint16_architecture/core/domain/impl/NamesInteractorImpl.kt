package com.example.sprint16_architecture.core.domain.impl

import com.example.sprint16_architecture.core.data.network.Resource
import com.example.sprint16_architecture.core.domain.api.NamesInteractor
import com.example.sprint16_architecture.core.domain.api.NamesRepository
import com.example.sprint16_architecture.core.domain.models.Person
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NamesInteractorImpl(
    private val repository: NamesRepository
) : NamesInteractor {


    override fun searchNames(expression: String): Flow<Pair<List<Person>?, String?>> {
        return repository.searchNames(expression).map { result ->
            when(result) {
                is Resource.Success -> { Pair(result.data, null)}
                is Resource.Error -> { Pair(null, result.message) }
            }
        }
    }
}