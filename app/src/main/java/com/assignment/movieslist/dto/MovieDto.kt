package com.assignment.movieslist.dto

import com.google.gson.annotations.SerializedName

data class MovieDto(

    @SerializedName("id")
    var movieId: Int,

    @SerializedName("title")
    var title: String,

    @SerializedName("poster_path")
    var poster_path: String?,

    @SerializedName("overview")
    var overview: String,

    @SerializedName("release_date")
    var release_date: String,

    @SerializedName("popularity")
    var popularity: Float,

    @SerializedName("genre_ids")
    var genre_ids: List<Int>?,

    @SerializedName("vote_average")
    var ratings: Float,

    @SerializedName("vote_count")
    var usersReview: Long

)

data class MovieResponse(
    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val movieDtoList: ArrayList<MovieDto>?,

    @SerializedName("total_results")
    val totalResults: Int,

    @SerializedName("total_pages")
    val totalPages: Int
)
