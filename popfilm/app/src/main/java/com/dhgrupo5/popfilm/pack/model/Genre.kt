package com.dhgrupo5.popfilm.pack.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Genre(
    val id: Int?,
    val name: String?,
    var movies: MutableList<MovieResponse>?
):Serializable
