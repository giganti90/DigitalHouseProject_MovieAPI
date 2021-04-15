package com.dhgrupo5.popfilm.pack.repository

import com.dhgrupo5.popfilm.pack.model.tmdb.auth.GuestSession
import com.dhgrupo5.popfilm.pack.utils.moviesdb.Endpoint
import com.dhgrupo5.popfilm.pack.utils.moviesdb.NetworkUtils

class MoviesAPIRepository {
    // Make this client a Singleton
    private val retrofitClient = NetworkUtils
            .getRetrofitInstance("https://api.themoviedb.org/3/")
    private val endpoint = retrofitClient.create(Endpoint::class.java)
    suspend fun getGuestSession() =
            endpoint.getGuestSession()

    suspend fun getGenres(language: String) =
            endpoint.getGenres(language).genres
}