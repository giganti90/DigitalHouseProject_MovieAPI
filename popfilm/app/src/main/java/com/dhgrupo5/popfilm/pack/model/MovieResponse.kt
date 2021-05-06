package com.dhgrupo5.popfilm.pack.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class MovieResponse(
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("id")
    val id: String,
    @SerializedName("poster_path")
    val posterPath: String,
    val title: String
) : Serializable
