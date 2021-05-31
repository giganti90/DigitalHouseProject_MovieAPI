package com.dhgrupo5.popfilm.pack.ui.activity.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dhgrupo5.popfilm.pack.model.GenreDCModelForCategories
import com.dhgrupo5.popfilm.pack.model.MovieResponse
import com.dhgrupo5.popfilm.pack.repository.MoviesAPIRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    private val _genres = MutableLiveData<List<MovieResponse>>()
    val genres:LiveData<List<MovieResponse>> =
        _genres

    private val moviesRepository = MoviesAPIRepository()

    suspend fun getGenres(genre: String) = CoroutineScope(Dispatchers.IO).launch {
        val genresList = moviesRepository.getGenres().genres?.forEach { genre ->
            genre
        }
    }
}
