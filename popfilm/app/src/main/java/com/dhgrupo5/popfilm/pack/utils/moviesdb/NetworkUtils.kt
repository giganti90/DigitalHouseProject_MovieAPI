package com.dhgrupo5.popfilm.pack.utils.moviesdb

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkUtils {
    companion object {
        /* Returns a Retrofit client instance
         * @param path API main path
         */
        const val API_KEY = "5166609185fe629466c2a9bd05c3b814"
        fun getRetrofitInstance(path: String) : Retrofit {
            return Retrofit.Builder()
                    .baseUrl(path)
                    .addConverterFactory((GsonConverterFactory.create()))
                    .build()
        }
    }
}