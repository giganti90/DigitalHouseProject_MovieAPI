package com.dhgrupo5.popfilm.pack.model.tmdb.movies

data class Genre(
    val id: Int,
    val name: String,
    var movies: List<Movie>
)