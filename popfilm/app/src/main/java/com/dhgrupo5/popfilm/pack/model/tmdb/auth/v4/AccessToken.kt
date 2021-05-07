package com.dhgrupo5.popfilm.pack.model.tmdb.auth.v4

data class AccessToken(
    val access_token: String,
    val account_id: String,
    val status_code: Int,
    val status_message: String,
    val success: Boolean
)