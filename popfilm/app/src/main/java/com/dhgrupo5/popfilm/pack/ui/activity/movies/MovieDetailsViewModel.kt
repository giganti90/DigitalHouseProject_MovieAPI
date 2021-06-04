package com.dhgrupo5.popfilm.pack.ui.activity.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dhgrupo5.popfilm.pack.model.GenreDCModelForCategories
import com.dhgrupo5.popfilm.pack.model.MovieResponse
import com.dhgrupo5.popfilm.pack.repository.MoviesAPIRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel: ViewModel() {
    private val _film = MutableLiveData<List<MovieResponse>>()
    val film: LiveData<List<MovieResponse>> =
        _film

    private val moviesRepository = MoviesAPIRepository()

}
