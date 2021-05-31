package com.dhgrupo5.popfilm.pack.ui.activity.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dhgrupo5.popfilm.pack.repository.MoviesAPIRepository

class HomeViewModel: ViewModel() {
    private val _genres = MutableLiveData<List<String>>()
    val genres:LiveData<List<String>> =
        _genres

    private val moviesRepository = MoviesAPIRepository()

}
