package com.dhgrupo5.popfilm.pack.utils.moviesdb

import com.dhgrupo5.popfilm.pack.model.tmdb.auth.v3.RequestToken
import retrofit2.Call
import retrofit2.http.GET

interface Endpoint {

    @GET("requestToken")
    fun getRequestToken(): Call<RequestToken>
}