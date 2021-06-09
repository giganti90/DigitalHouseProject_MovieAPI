package com.dhgrupo5.popfilm.pack.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dhgrupo5.popfilm.pack.model.MovieResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviePlayViewModel: ViewModel() {

    lateinit var liveData:LiveData<MovieResponse>

    fun getUrlTrailler() = CoroutineScope(Dispatchers.IO).launch {

    }


}