package com.dhgrupo5.popfilm.pack.model.tmdb.movies

data class GenreT(
    val id: Int,
    val name: String,
    var movies: List<Movie> = listOf()
)