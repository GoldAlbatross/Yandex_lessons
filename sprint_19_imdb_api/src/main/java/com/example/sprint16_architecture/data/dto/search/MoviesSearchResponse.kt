package com.example.sprint16_architecture.data.dto.search

import com.example.sprint16_architecture.data.dto.Response


data class MoviesSearchResponse(
    val searchType: String,
    val expression: String,
    val results: List<MovieDto>
): Response()