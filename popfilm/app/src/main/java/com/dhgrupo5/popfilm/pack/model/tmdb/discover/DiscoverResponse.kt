package com.dhgrupo5.popfilm.pack.model.tmdb.discover

import com.google.gson.*
import com.google.gson.annotations.*
import java.util.*

data class DiscoverResponse(
    val page: Int? = null,
    @SerializedName
    val results: List<Result>? = null,

    val total_pages: Int? = null,
    val total_results: Int? = null
)