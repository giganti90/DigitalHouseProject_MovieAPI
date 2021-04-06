package com.dhgrupo5.popfilm.pack.utils.moviesdb

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkUtils {
    companion object {
        /* Returns a Retrofit client instance
         * @param path API main path
         */
        fun getRetrofitInstance(path: String) : Retrofit {
            return Retrofit.Builder()
                    .baseUrl(path)
                    .addConverterFactory((GsonConverterFactory.create()))
                    .build()
        }
    }
}