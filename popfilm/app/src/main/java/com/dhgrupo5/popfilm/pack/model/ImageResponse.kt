package com.dhgrupo5.popfilm.pack.model

import com.google.gson.annotations.SerializedName

data class ImageResponse(
//    @SerializedName("poster_path")
//    val posterPath: String,
    val images: List<Image>
)
