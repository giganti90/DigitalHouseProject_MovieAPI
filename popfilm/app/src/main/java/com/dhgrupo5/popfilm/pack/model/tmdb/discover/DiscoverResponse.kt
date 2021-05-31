package com.dhgrupo5.popfilm.pack.model.tmdb.discover

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class DiscoverResponse(
    val page: Int? = null,
    @SerializedName("results")
    val movie: List<Movie>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null
) : Serializable