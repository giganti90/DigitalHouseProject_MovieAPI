package com.dhgrupo5.popfilm.pack.utils.moviesdb

import com.dhgrupo5.popfilm.pack.model.tmdb.auth.GuestSession
import com.dhgrupo5.popfilm.pack.model.tmdb.auth.v3.RequestToken
import retrofit2.Call
import retrofit2.http.GET

interface Endpoint {

    @GET("authentication/token/new?api_key={api_key}")
    fun getRequestToken(): Call<RequestToken>

    @GET("authentication/guest_session/new?api_key=")
    fun getGuestSession() : Call<GuestSession>

}