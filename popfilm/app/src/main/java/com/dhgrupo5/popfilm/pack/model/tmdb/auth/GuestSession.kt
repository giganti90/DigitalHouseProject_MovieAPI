package com.dhgrupo5.popfilm.pack.model.tmdb.auth

data class GuestSession(
    val expires_at: String = "",
    val guest_session_id: String = "",
    val success: Boolean = false
)