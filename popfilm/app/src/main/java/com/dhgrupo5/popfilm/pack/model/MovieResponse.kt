package com.dhgrupo5.popfilm.pack.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class MovieResponse(
    @SerializedName("genre_ids")
    val genreIds: List<String>,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("id")
    val id: String,
    val title: String,
    @SerializedName("overview")
    val overview: String?,
    val url:String,
    @SerializedName("year")
    val releaseDateYear: String?
) : Serializable
