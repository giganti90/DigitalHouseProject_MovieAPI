package com.dhgrupo5.popfilm.pack.model

import com.google.gson.annotations.SerializedName

data class DiscoverResponse(
        @SerializedName("page")
        val page: Int?,
        @SerializedName("results")
        val movies: List<MovieResponse>?,
        @SerializedName("total_pages")
        val totalPages: Int?,
        @SerializedName("total_results")
        val totalResults: Int?
)
