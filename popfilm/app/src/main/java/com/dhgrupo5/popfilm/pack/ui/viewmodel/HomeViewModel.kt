package com.dhgrupo5.popfilm.pack.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dhgrupo5.popfilm.pack.model.tmdb.movies.Genre
import com.dhgrupo5.popfilm.pack.model.tmdb.movies.Movie
import com.dhgrupo5.popfilm.pack.repository.MoviesAPIRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    // Movie genres LiveData
    private val _genresLiveData = MutableLiveData<List<Genre>>()
    val genresLiveData: LiveData<List<Genre>> =
            _genresLiveData
    // Movies LiveData
    private val _moviesLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData: LiveData<List<Movie>> =
            _moviesLiveData


    // Trending movies LiveData

    // MoviesAPIRepository
    private val moviesAPIRepository = MoviesAPIRepository()
    // Movie genres
    fun getGenres(language: String) = CoroutineScope(Dispatchers.IO).launch {
        _genresLiveData.postValue(
                moviesAPIRepository.getGenres(language).genres)
    }
    // Movies
    fun getMoviesByGenre(genre: String) = CoroutineScope(Dispatchers.IO).launch {
        val moviesList = listOf<Movie>()
        moviesAPIRepository.getMoviesByGenre(genre).movies
        _moviesLiveData.postValue(moviesList)
    }
}