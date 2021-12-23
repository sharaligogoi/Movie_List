package com.assignment.movieslist.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenresModel(
    var id: Int,
    var name: String
): Parcelable
