package com.dicoding.mymovie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val title: String,
    val description: String,
    val players: String,
    val releaseYear: String,
    val photo: Int
) : Parcelable