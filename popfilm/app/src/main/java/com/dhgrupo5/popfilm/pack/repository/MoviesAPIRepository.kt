package com.dhgrupo5.popfilm.pack.repository

import com.dhgrupo5.popfilm.pack.model.*
import com.dhgrupo5.popfilm.pack.model.GenreList.genres
import com.dhgrupo5.popfilm.pack.model.tmdb.auth.GuestSession
import com.dhgrupo5.popfilm.pack.utils.moviesdb.Endpoint
import com.dhgrupo5.popfilm.pack.utils.moviesdb.NetworkUtils

class MoviesAPIRepository {

    val page: Int? = 1
    val orderBy: String? = "name"

    private val retrofitClient = NetworkUtils
            .getRetrofitInstance("https://api.themoviedb.org/3/")

    private val endpoint = retrofitClient.create(Endpoint::class.java)

    suspend fun getGuestSession() : GuestSession =
            endpoint.getGuestSession()

    suspend fun getMovieGenre() : GenresResponse =
        endpoint.getGenre()

//    suspend fun getMovieID() : MovieResponse =
//        endpoint.getMovieID()
//
//    suspend fun getMovieMedia() : ImageResponse =
//        endpoint.getImages() //que porra de path é esse aqui?

//    suspend fun getMovieService() = endpoint.discoverMovies(genresInclude = "Action")

    suspend fun getMovieService() =
            endpoint.discoverMovies(
                    genresInclude = genres
            )
}
