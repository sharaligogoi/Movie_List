package com.assignment.movieslist.dto

import com.google.gson.annotations.SerializedName

data class GenresDto(

    @SerializedName("id")
    var id:Int,

    @SerializedName("name")
    var name: String
        )


data class GenreResponse(
    @SerializedName("genres")
    var genreDtoList: List<GenresDto>
)
