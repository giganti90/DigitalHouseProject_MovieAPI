package com.dhgrupo5.popfilm.pack.ui.activity.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dhgrupo5.popfilm.pack.model.GenreDCModelForCategories
import com.dhgrupo5.popfilm.pack.repository.MoviesAPIRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    private val _genres = MutableLiveData<List<GenreDCModelForCategories>>()
    val genres:LiveData<List<GenreDCModelForCategories>> =
        _genres

    private val moviesRepository = MoviesAPIRepository()

    fun getGenres() = CoroutineScope(Dispatchers.IO).launch {
        val genresList = moviesRepository.getGenres().genres?.toMutableList() ?: listOf()
        genresList.forEach { genre ->
            genre.movies = moviesRepository.getMoviesByGenre(genre.id).movies.toMutableList()
        }
        _genres.postValue(genresList)
    }
}
