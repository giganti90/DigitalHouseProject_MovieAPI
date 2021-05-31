package com.dhgrupo5.popfilm.pack.model.tmdb.discover

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DiscoverResponse(
    val page: Int? = null,
    @SerializedName("results")
    val movies: List<Movie>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null
) : Serializable