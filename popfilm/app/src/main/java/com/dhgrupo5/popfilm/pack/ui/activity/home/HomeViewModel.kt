package com.dhgrupo5.popfilm.pack.ui.activity.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {
    private val _genres = MutableLiveData<List<String>>()
    val genres:LiveData<List<String>> =
        _genres
}
