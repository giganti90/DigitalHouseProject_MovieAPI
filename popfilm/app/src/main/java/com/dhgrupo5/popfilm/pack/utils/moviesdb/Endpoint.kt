package com.dhgrupo5.popfilm.pack.utils.moviesdb

import android.view.textclassifier.TextLanguage
import com.dhgrupo5.popfilm.pack.model.tmdb.auth.GuestSession
import com.dhgrupo5.popfilm.pack.model.tmdb.auth.v3.RequestToken
import com.dhgrupo5.popfilm.pack.model.tmdb.movies.DiscoverResponse
import com.dhgrupo5.popfilm.pack.model.tmdb.movies.GenresResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Endpoint {

    @GET("authentication/token/new?api_key=${NetworkUtils.API_KEY}")
    suspend fun getRequestToken(): RequestToken

    @GET("authentication/guest_session/new?api_key=${NetworkUtils.API_KEY}")
    suspend fun getGuestSession() : GuestSession

    @GET("genre/movie/list?api_key=${NetworkUtils.API_KEY}")
    suspend fun getGenres(@Query("language") language: String) : GenresResponse

    @GET("discover/movie?api_key=${NetworkUtils.API_KEY}&include_adult=true&page=1&with_genres=35")
    suspend fun discoverMovies(
            @Query("sort_by=popularity.desc") sortBy: String,
            @Query("language") language: String)
    : DiscoverResponse

}