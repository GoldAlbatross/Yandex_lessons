package com.example.sprint16_architecture.core.data.converters

import com.example.sprint16_architecture.core.data.dto.cast.ActorResponse
import com.example.sprint16_architecture.core.data.dto.cast.DirectorsResponse
import com.example.sprint16_architecture.core.data.dto.cast.Item
import com.example.sprint16_architecture.core.data.dto.cast.MovieCastResponse
import com.example.sprint16_architecture.core.data.dto.cast.OtherResponse
import com.example.sprint16_architecture.core.data.dto.cast.WritersResponse
import com.example.sprint16_architecture.core.domain.models.MovieCast
import com.example.sprint16_architecture.core.domain.models.MovieCastPerson

class MovieCastConverter {

    fun convert(response: MovieCastResponse): MovieCast {
        return with(response) {
            MovieCast(
                imdbId = this.imDbId,
                fullTitle = this.fullTitle,
                directors = convertDirectors(this.directors),
                others = convertOthers(this.others),
                writers = convertWriters(this.writers),
                actors = convertActors(this.actors)
            )
        }
    }

    private fun convertDirectors(directors: DirectorsResponse): List<MovieCastPerson> {
        return directors.items.map { it.toMovieCastPerson() }
    }

    private fun convertOthers(others: List<OtherResponse>): List<MovieCastPerson> {
        return others.flatMap { other ->
            other.items.map { it.toMovieCastPerson(jobPrefix = other.job) }
        }
    }

    private fun convertWriters(writersResponse: WritersResponse): List<MovieCastPerson> {
        return writersResponse.items.map { it.toMovieCastPerson() }
    }

    private fun convertActors(actors: List<ActorResponse>): List<MovieCastPerson> {
        return actors.map { actor ->
            MovieCastPerson(
                id = actor.id,
                name = actor.name,
                description = actor.asCharacter,
                image = actor.image,
            )
        }
    }

    private fun Item.toMovieCastPerson(jobPrefix: String = ""): MovieCastPerson {
        return MovieCastPerson(
            id = this.id,
            name = this.name,
            description = if (jobPrefix.isEmpty()) this.description else "$jobPrefix -- ${this.description}",
            image = null,
        )
    }

}