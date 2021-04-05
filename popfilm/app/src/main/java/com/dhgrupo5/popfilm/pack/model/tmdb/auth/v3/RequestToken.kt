package com.dhgrupo5.popfilm.pack.model.tmdb.auth.v3

data class RequestToken(
    val expires_at: String,
    val request_token: String,
    val success: Boolean
)