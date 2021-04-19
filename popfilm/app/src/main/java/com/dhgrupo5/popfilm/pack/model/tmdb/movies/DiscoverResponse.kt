package com.dhgrupo5.popfilm.pack.model.tmdb.movies

import com.google.gson.annotations.SerializedName

data class DiscoverResponse(
        val page: Int,
        @SerializedName("results")
        val movies: List<Movie>,
        val total_pages: Int,
        val total_results: Int
)