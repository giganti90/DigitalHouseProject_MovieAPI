package com.dhgrupo5.popfilm.pack.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dhgrupo5.popfilm.pack.model.Genre
import com.dhgrupo5.popfilm.pack.model.Movie
import com.dhgrupo5.popfilm.pack.model.MovieConfig
import com.dhgrupo5.popfilm.pack.model.MovieResponse
import com.dhgrupo5.popfilm.pack.repository.MoviesAPIRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MovieVM : ViewModel() {

    private val repository = MoviesAPIRepository()
    val _listGenre = MutableLiveData<List<Genre>>()
    val listGenre: MutableLiveData<List<Genre>> = MutableLiveData()
    val movieList = MutableLiveData<List<Movie>>()
    var genreApi = mutableListOf<Genre>()

//    fun getMovieDetail(movieId: String) = CoroutineScope(Dispatchers.IO).launch {
//        try {
//            repository.getMovieDetails(movieId).let {
//                movieDetail.postValue(it)
//            }
//
//        } catch (error: Throwable){
//            Log.e("Error", "Problema de Conexão $error")
//        }
//    }
//
//    fun getBackdropPath(): String{
//        imageUrl = "${configuration?.images?.base_url}${configuration?.images?.backdrop_sizes?.last()}${movieDetail.value?.backdropPath}"
//        return imageUrl
//    }

    fun getMovieGenre() = CoroutineScope(Dispatchers.IO).launch {
        try {
            repository.getConfiguration().let { configuration ->
                MovieConfig.setConfiguration(configuration)
                getGenre()
            }
        } catch (error: Throwable) {
            Log.e("Error", "Problema de Configuration $error")
        }
    }

    fun getGenre() = CoroutineScope(Dispatchers.IO).launch {
        try {
            repository.getGenre().let {
                _listGenre.postValue(it.genres!!)
                genreApi = it.genres as MutableList<Genre>
                genreApi.forEach { genreApi ->
                    repository.getMoviesByGenre(genreApi.id.toString()).let { movie ->
                        genreApi.movies = movie.movies as MutableList<MovieResponse>?
                    }
                }
            }
            listGenre.postValue(genreApi)
        } catch (error: Throwable) {
            Log.e("Error", "Problema de Genre $error")
        }
    }
}
