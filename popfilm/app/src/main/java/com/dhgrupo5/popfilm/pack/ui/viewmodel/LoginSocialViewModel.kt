package com.dhgrupo5.popfilm.pack.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dhgrupo5.popfilm.pack.model.tmdb.auth.GuestSession
import com.dhgrupo5.popfilm.pack.repository.MoviesAPIRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginSocialViewModel:ViewModel() {

    val guestSession = MutableLiveData<GuestSession>()
    private val repository = MoviesAPIRepository()

    fun getGuestSession() = CoroutineScope(Dispatchers.IO).launch {
        repository.getGuestSession().let { _guestSession ->
            guestSession.postValue(_guestSession)
        }
    }
 }