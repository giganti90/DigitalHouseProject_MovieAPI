package com.dhgrupo5.popfilm.pack.model

import com.google.gson.annotations.SerializedName

data class DiscoverResponse(
        val page: Int,
        @SerializedName("results")
        val moviesList: List<Movie>,
        val total_pages: Int,
        val total_results: Int,
        val genreslist: List<Genre>
)
