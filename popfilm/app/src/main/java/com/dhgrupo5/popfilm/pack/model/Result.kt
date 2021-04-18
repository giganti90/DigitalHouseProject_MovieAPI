package com.dhgrupo5.popfilm.pack.model

import com.google.gson.annotations.SerializedName
import retrofit2.http.Url

data class Result(
        @SerializedName("title")
        val title: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("genre_ids")
        val genres: GenresResponse,
        @SerializedName("poster_path")
        val posterPath: List<Poster>
)
