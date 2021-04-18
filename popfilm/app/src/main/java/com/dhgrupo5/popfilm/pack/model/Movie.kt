package com.dhgrupo5.popfilm.pack.model

import com.google.gson.annotations.SerializedName
import retrofit2.http.Query

data class Movie(
        @SerializedName("id") val id: Int,
        @SerializedName("title") val title: String,
        @SerializedName("release_date") val releaseDate: String,
        @SerializedName("poster_path") val posterPath: String,
        @SerializedName("backdrop_path") val backdropPath: String,
        @SerializedName("overview") val overview: String,
        @Query("genre_id") val genreIds: List<Int>
    )

//data class Movie(
//        val adult: Boolean,
//        val backdrop_path: String,
//        val genre_ids: List<Int>,
//        val id: Int,
//        val original_language: String,
//        val original_title: String,
//        val popularity: Double,
//        val poster_path: String,
//        val release_date: String,
//        val title: String,
//        val video: Boolean,
//        val vote_average: Double,
//        val vote_count: Int
//)
