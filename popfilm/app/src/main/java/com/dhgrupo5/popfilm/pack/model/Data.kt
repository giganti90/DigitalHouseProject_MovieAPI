package com.dhgrupo5.popfilm.pack.model

import com.google.gson.annotations.SerializedName


data class Data(
        @SerializedName("results")
        val results: List<Result>
)
