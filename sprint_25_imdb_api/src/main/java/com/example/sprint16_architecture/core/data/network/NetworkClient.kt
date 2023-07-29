package com.example.sprint16_architecture.core.data.network

import com.example.sprint16_architecture.core.data.dto.Response

interface NetworkClient {
    fun doRequest(dto: Any): Response
    suspend fun doRequestSuspend(dto: Any): Response
}