package com.dhgrupo5.popfilm.pack.ui.viewmodel

import android.util.Log
import android.view.textclassifier.TextLanguage
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dhgrupo5.popfilm.pack.model.tmdb.movies.Genre
import com.dhgrupo5.popfilm.pack.repository.MoviesAPIRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    // Movie genres LiveData
    private val _genres = MutableLiveData<List<Genre>>()
    val genres: LiveData<List<Genre>> =
            _genres

    // Trending movies LiveData

    // MoviesAPIRepository
    private val moviesAPIRepository = MoviesAPIRepository()
    fun getGenres(language: String) = CoroutineScope(Dispatchers.IO).launch {
        moviesAPIRepository.getGenres(language).let {
            var list = mutableListOf<Genre>()
            it.forEach { genre ->
                list.add(genre)
            }
            Log.d("list-API",list.toString())
            _genres.value = list
            Log.d("response-API",it.toString())
            Log.d("LiveData-API",genres.value.toString())
            Log.d("MutableLiveData-API",_genres.value.toString())
        }
    }
}