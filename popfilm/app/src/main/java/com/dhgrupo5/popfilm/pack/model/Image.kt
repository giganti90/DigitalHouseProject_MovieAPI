package com.dhgrupo5.popfilm.pack.model

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("aspect_ratio")
    val aspectRatio: Double,
    val height: Int,
    val width: Int
)
