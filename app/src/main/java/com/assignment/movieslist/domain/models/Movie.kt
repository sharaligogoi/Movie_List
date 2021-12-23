package com.assignment.movieslist.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
                 val movieId: Int,
                 val title: String,
                 val description: String,
                 val releaseDate: String,
                 val posterUrl: String?,
                 val genreIds: List<Int>,
                 val ratings: Float,
                 val usersReview: Long
) : Parcelable
