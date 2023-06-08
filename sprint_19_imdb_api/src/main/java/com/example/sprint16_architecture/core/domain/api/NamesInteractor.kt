package com.example.sprint16_architecture.core.domain.api

import com.example.sprint16_architecture.core.domain.models.Person

interface NamesInteractor {

    fun searchNames(expression: String, consumer: NamesConsumer)

    interface NamesConsumer {
        fun consume(foundNames: List<Person>?, errorMessage: String?)
    }
}