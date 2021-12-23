package com.assignment.movieslist.api

import com.assignment.movieslist.dto.GenreResponse
import com.assignment.movieslist.dto.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiServices {

    @GET("discover/movie?sort_by=popularity.desc&include_adult=false&include_video=false")
    suspend fun getMovieResponse(@Query("page")page:Int = 1): MovieResponse

    @GET("genre/movie/list")
    suspend fun getGenreResponse(): GenreResponse
}