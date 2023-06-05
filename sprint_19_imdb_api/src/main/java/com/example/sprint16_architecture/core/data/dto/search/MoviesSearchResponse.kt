package com.example.sprint16_architecture.core.data.dto.search

import com.example.sprint16_architecture.core.data.dto.Response


data class MoviesSearchResponse(
    val searchType: String,
    val expression: String,
    val results: List<MovieDto>
): Response()