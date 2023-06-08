package com.example.sprint16_architecture.core.data.dto.details

import com.example.sprint16_architecture.core.data.dto.Response

data class MovieDetailsResponse(
    val id: String,
    val title: String,
    val imDbRating: String,
    val year: String,
    val countries: String,
    val genres: String,
    val directors: String,
    val writers: String,
    val stars: String,
    val plot: String
) : Response()