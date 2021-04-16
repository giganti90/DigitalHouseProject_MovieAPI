package com.dhgrupo5.popfilm.pack.utils.moviesdb

import com.dhgrupo5.popfilm.pack.model.GenresResponse
import com.dhgrupo5.popfilm.pack.model.MovieResponse
import com.dhgrupo5.popfilm.pack.model.ImageResponse
import com.dhgrupo5.popfilm.pack.model.tmdb.auth.GuestSession
import com.dhgrupo5.popfilm.pack.model.tmdb.auth.v3.RequestToken
import retrofit2.http.GET
import retrofit2.http.Path

interface Endpoint {

    @GET("authentication/token/new?api_key=${NetworkUtils.API_KEY}")
    suspend fun getRequestToken(): RequestToken

    @GET("authentication/guest_session/new?api_key=${NetworkUtils.API_KEY}")
    suspend fun getGuestSession() : GuestSession

    @GET("genre/movie/list?api_key=${NetworkUtils.API_KEY}")
    suspend fun getGenre() : GenresResponse

    @GET("movie/{movie_id}?api_key=${NetworkUtils.API_KEY}")
    suspend fun getMovieID() : MovieResponse

//    @GET("movie/{id}/images?api_key=${NetworkUtils.API_KEY}")
//    suspend fun getMovieMedia(@Path("id") movieId: ImageResponse)

    @GET("movie/{movie_id}/images?api_key=${NetworkUtils.API_KEY}")
    suspend fun getImages(): ImageResponse

}
