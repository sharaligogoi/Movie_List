package com.assignment.movieslist.mappers

import com.assignment.movieslist.domain.models.GenresModel
import com.assignment.movieslist.domain.models.Movie
import com.assignment.movieslist.dto.GenresDto
import com.assignment.movieslist.dto.MovieDto

class MovieDtoMapper(var genreDtoMapper: GenreDtoMapper) {

    fun mapToDomain(entity: MovieDto): Movie{
        return Movie(
            movieId = entity.movieId,
            title = entity.title,
            description = entity.overview,
            releaseDate = entity.release_date,
            posterUrl = entity.poster_path?.let { "https://www.themoviedb.org/t/p/w220_and_h330_face$it" },
            genreIds = entity.genre_ids,
            ratings = entity.ratings,
            usersReview = entity.usersReview
        )
    }

}

class GenreDtoMapper()  {
   fun mapToDomain(entity: GenresDto): GenresModel {
        return GenresModel(
            id = entity.id,
            name = entity.name
        )
    }

    fun mapFromDomain(domainModel: GenresModel): GenresDto {
        throw IllegalStateException("Not mapping to dto model, outbound data not in use")
    }
}