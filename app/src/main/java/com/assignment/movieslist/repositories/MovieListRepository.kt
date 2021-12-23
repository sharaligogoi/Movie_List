package com.assignment.movieslist.repositories

import com.assignment.movieslist.api.MovieApiServices
import com.assignment.movieslist.api.MovieServiceBuilder
import com.assignment.movieslist.domain.models.GenresModel
import com.assignment.movieslist.domain.models.Movie
import com.assignment.movieslist.dto.MovieResponse
import com.assignment.movieslist.mappers.GenreDtoMapper
import com.assignment.movieslist.mappers.MovieDtoMapper

object MovieListRepository {

    private val movieApiService = MovieServiceBuilder.buildMovieServices(MovieApiServices::class.java)

    suspend fun getMoviesList(page: Int): List<Movie>? {


        val genreDtoMapper = GenreDtoMapper()

        val movieMapper = MovieDtoMapper(genreDtoMapper)

        val response: MovieResponse = movieApiService.getMovieResponse(page)

        return response.movieDtoList?.map {
            movieMapper.mapToDomain(it)
        }
    }

    suspend fun getGenresModelMap(): Map<Int,GenresModel>{
        val genreResponse = movieApiService.getGenreResponse()

        val genreDtoMapper = GenreDtoMapper()
        return  genreResponse.genreDtoList
            .map {
                it.id to genreDtoMapper.mapToDomain(it)
            }.toMap()

    }

}




