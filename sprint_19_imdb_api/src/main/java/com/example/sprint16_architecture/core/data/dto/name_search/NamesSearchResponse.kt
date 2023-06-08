package com.example.sprint16_architecture.core.data.dto.name_search

import com.example.sprint16_architecture.core.data.dto.Response

class NamesSearchResponse(
    val searchType: String,
    val expression: String,
    val results: List<PersonDto>
) : Response()