package com.example.sprint16_architecture.core.domain.impl

import com.example.sprint16_architecture.core.data.network.Resource
import com.example.sprint16_architecture.core.domain.api.NamesInteractor
import com.example.sprint16_architecture.core.domain.api.NamesRepository
import java.util.concurrent.Executors

class NamesInteractorImpl(
    private val repository: NamesRepository
) : NamesInteractor {

    private val executor = Executors.newCachedThreadPool()

    override fun searchNames(expression: String, consumer: NamesInteractor.NamesConsumer) {
        executor.execute {
            when(val resource = repository.searchNames(expression)) {
                is Resource.Success -> { consumer.consume(resource.data, null) }
                is Resource.Error -> { consumer.consume(resource.data, resource.message) }
            }
        }
    }
}