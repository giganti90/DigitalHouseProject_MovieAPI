package com.dhgrupo5.popfilm.pack.model

import com.google.gson.*
import retrofit2.http.Url

//data class Result(
//        val discoverResponse: DiscoverResponse,
//        @SerializedName("title")
//        val title: String,
//        @SerializedName("id")
//        val id: Int,
//        @SerializedName("overview")
//        val overview: String,
//        @SerializedName("genre_ids")
//        val genres: GenresResponse,
//        @SerializedName("poster_path")
//        val posterPath: List<Poster>,
//)
data class Result(
    val id: String,
    val iso_3166_1: String,
    val iso_639_1: String,
    val key: String,
    val name: String,
    val site: String,
    val size: Int,
    val type: String
)
