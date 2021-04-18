package com.dhgrupo5.popfilm.pack.model

import com.google.gson.annotations.SerializedName

data class MovieDetail (
    val change_keys: List<String>? = listOf(),
    val images: Image? = Image()
)

