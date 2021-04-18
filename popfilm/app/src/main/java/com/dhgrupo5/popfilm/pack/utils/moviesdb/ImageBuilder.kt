package com.dhgrupo5.popfilm.pack.utils.moviesdb

import com.dhgrupo5.popfilm.pack.repository.MoviesAPIRepository
import com.dhgrupo5.popfilm.pack.utils.moviesdb.NetworkUtils.Companion.API_KEY

private val POSTER_URL = "https://image.tmdb.org/t/p/w154"
private val BACKDROP_URL = "https://image.tmdb.org/t/p/w780"

class ImageBuilder {

    fun buildPosterUrl(posterPath: String): String {
        return POSTER_URL + posterPath + "?api_key=" + NetworkUtils.API_KEY
    }

    fun buildBackdropUrl(backdropPath: String): String {
        return BACKDROP_URL + backdropPath + "?api_key=" + NetworkUtils.API_KEY
    }
}
