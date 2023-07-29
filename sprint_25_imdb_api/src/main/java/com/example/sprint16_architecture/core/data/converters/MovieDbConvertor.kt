package com.example.sprint16_architecture.core.data.converters

import com.example.sprint16_architecture.core.data.dto.search.MovieDto
import com.example.sprint16_architecture.core.data.storage.db.entity.MovieEntity
import com.example.sprint16_architecture.core.domain.models.Movie

class MovieDbConvertor {

    fun map(movie: MovieDto): MovieEntity {
        return with(movie) {
            MovieEntity(id, resultType, image, title, description)
        }
    }

    fun map(movie: MovieEntity): Movie {
        return with(movie) {
            Movie(id, resultType, image, title, description, false)
        }
    }


}