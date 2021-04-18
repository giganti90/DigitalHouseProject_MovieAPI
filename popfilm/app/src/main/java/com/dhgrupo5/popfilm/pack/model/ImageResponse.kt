package com.dhgrupo5.popfilm.pack.model

import com.google.gson.annotations.SerializedName

data class ImageResponse(
        val backdrops: List<Backdrop>,
        val id: Int,
        val posters: List<Poster>
)
