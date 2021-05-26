package com.dhgrupo5.popfilm.pack.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GenreDCModelForCategories(
    val id: String,
    val name: String,
    var movies: MutableList<MovieResponse>?
):Serializable


//versão correta
