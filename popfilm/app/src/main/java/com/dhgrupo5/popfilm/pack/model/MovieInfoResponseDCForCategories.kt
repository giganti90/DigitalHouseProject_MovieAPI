package com.dhgrupo5.popfilm.pack.model

import com.google.gson.annotations.SerializedName

data class MovieInfoResponseDCForCategories(
//    @SerializedName("dates")
//    val dates: Dates?,
        @SerializedName("page")
    val page: Int?,
        @SerializedName("results")
    val releaseMovieDCForCategories: List<MovieDCForCategories>?,
        @SerializedName("total_pages")
    val totalPages: Int?,
        @SerializedName("total_results")
    val totalResults: Int?
)
