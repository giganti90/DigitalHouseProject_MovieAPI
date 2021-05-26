package com.dhgrupo5.popfilm.pack.utils.moviesdb

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass

private val gsonConverter: GsonConverterFactory = GsonConverterFactory.create()

class RetrofitInit(url: String) {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(OkHttpClient.Builder().apply {
            addInterceptor(APIKeyInterceptor())
            addInterceptor(loggingInterceptor)
        }.build())
        .addConverterFactory(gsonConverter)
        .build()

    fun <T : Any> create(clazz: KClass<T>): T = retrofit.create(clazz.java)

}
