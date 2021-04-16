package com.dhgrupo5.popfilm.pack.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("movie_list")
    val movieList: MutableList<Movie>,

)

// ta certo isso aqui? "genres": [{ "id"}]"
