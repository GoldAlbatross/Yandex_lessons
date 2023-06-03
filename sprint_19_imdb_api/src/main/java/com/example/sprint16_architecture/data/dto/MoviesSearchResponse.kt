package com.example.sprint16_architecture.data.dto


data class MoviesSearchResponse(
    val searchType: String,
    val expression: String,
    val results: List<MovieDto>
): Response()