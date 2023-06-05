package com.example.sprint16_architecture.data.network

import com.example.sprint16_architecture.data.dto.Response

interface NetworkClient {
    fun doRequest(dto: Any): Response
}