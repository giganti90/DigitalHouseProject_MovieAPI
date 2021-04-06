package com.dhgrupo5.popfilm.pack.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.dhgrupo5.popfilm.pack.model.tmdb.auth.GuestSession
import com.dhgrupo5.popfilm.pack.model.tmdb.auth.v3.RequestToken
import com.dhgrupo5.popfilm.pack.utils.moviesdb.Endpoint
import com.dhgrupo5.popfilm.pack.utils.moviesdb.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginSocialViewModel:ViewModel() {

    private val retrofitClient = NetworkUtils
            .getRetrofitInstance("https://api.themoviedb.org/3")
    val endpoint = retrofitClient.create(Endpoint::class.java)
    fun getRequestToken(): RequestToken {
        var _requestToken = RequestToken()
        endpoint.getRequestToken().enqueue(object : Callback<RequestToken> {
            override fun onResponse(call: Call<RequestToken>, response: Response<RequestToken>) {
                _requestToken = RequestToken(
                        response.body()?.expires_at ?: "",
                        response.body()?.request_token ?: "",
                        response.body()?.success ?: false
                )
            }

            override fun onFailure(call: Call<RequestToken>, t: Throwable) {
                Log.d("API","Failed fetching value")
            }

        })
        return _requestToken
    }
    fun getGuestSession(): GuestSession {
        var _guestSession = GuestSession()
        endpoint.getGuestSession().enqueue(object : Callback<GuestSession> {
            override fun onResponse(call: Call<GuestSession>, response: Response<GuestSession>) {
                _guestSession = GuestSession(
                        response.body()?.expires_at ?: "",
                        response.body()?.guest_session_id ?: "",
                        response.body()?.success ?: false
                )
            }

            override fun onFailure(call: Call<GuestSession>, t: Throwable) {
                Log.d("API","Failed fetching value")
            }
        })
        return _guestSession
    }
}