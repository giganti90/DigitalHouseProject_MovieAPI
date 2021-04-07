package com.dhgrupo5.popfilm.pack.model.tmdb.auth.v4

data class RequestToken(
    val request_token: String,
    val status_code: Int,
    val status_message: String,
    val success: Boolean
)