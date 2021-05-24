package com.dhgrupo5.popfilm.pack.repository

import com.dhgrupo5.popfilm.pack.model.*
import com.dhgrupo5.popfilm.pack.model.tmdb.auth.GuestSession
import com.dhgrupo5.popfilm.pack.utils.moviesdb.Endpoint
import com.dhgrupo5.popfilm.pack.utils.moviesdb.NetworkUtils
import com.dhgrupo5.popfilm.pack.utils.moviesdb.RetrofitInit

class MoviesAPIRepository {

    companion object {
        const val key = NetworkUtils.API_KEY
    }

    private val retrofitClient = NetworkUtils
            .getRetrofitInstance("https://api.themoviedb.org/3/")

    private var endpoint = retrofitClient.create(Endpoint::class.java)

    suspend fun getGuestSession() : GuestSession =
            endpoint.getGuestSession()

    suspend fun getMovieGenre() : GenresResponseForCategories =
        endpoint.getGenre("pt-br")



    private var url = "https://api.themoviedb.org/3/"
    private var service = Endpoint::class
    val language = "pt-BR"

    private val serviceMovie = RetrofitInit(url).create(service)

    suspend fun getGenre() = serviceMovie.getGenre(language)
    suspend fun getMoviesByGenre(genre:String) = serviceMovie.getMoviesByGenre(language, genre)
    suspend fun getMovieDetails(movieID: String) = serviceMovie.getMovieDetail(movieID, language)


}
