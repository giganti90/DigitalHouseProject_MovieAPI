package com.dhgrupo5.popfilm.pack.model.tmdb.movies

import com.dhgrupo5.popfilm.pack.model.Movie

data class Genre(
    val id: Int,
    val name: String,
    val movies: List<Movie>
)