package com.example.sprint16_architecture.core.data

import com.example.sprint16_architecture.core.data.dto.name_search.NamesSearchRequest
import com.example.sprint16_architecture.core.data.dto.name_search.NamesSearchResponse
import com.example.sprint16_architecture.core.data.network.NetworkClient
import com.example.sprint16_architecture.core.data.network.Resource
import com.example.sprint16_architecture.core.domain.api.NamesRepository
import com.example.sprint16_architecture.core.domain.models.Person

class NamesRepositoryImpl(
    private val networkClient: NetworkClient
) : NamesRepository {

    override fun searchNames(expression: String): Resource<List<Person>> {
        val response = networkClient.doRequest(NamesSearchRequest(expression))
        return when (response.resultCode) {
            200 -> {
                with(response as NamesSearchResponse) {
                    Resource.Success(results.map {
                        Person(id = it.id, name = it.title, description = it.description, photoUrl = it.image)
                    })
                }
            }
            -1 -> Resource.Error("Проверьте подключение к интернету")
            else -> Resource.Error("Ошибка сервера")
        }
    }
}