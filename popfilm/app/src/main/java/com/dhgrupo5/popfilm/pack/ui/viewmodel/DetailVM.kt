package com.dhgrupo5.popfilm.pack.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dhgrupo5.popfilm.pack.model.Movie
import com.dhgrupo5.popfilm.pack.model.MovieConfig
import com.dhgrupo5.popfilm.pack.repository.MoviesAPIRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailVM: ViewModel() {

    private val repository = MoviesAPIRepository()
    val movieDetail by lazy{ MutableLiveData<Movie>() }
    val configuration = MovieConfig.config
    var imageUrl: String = ""

    fun getMovieDetail(movieId: String) = CoroutineScope(Dispatchers.IO).launch {
        try {
            repository.getMovieDetails(movieId).let {
                movieDetail.postValue(it)
            }

        } catch (error: Throwable){
            Log.e("Error", "Problema de Conexão $error")
        }
    }

    fun getBackdropPath(): String{
        imageUrl = "${configuration?.images?.base_url}${configuration?.images?.backdrop_sizes?.last()}${movieDetail.value?.backdropPath}"
        return imageUrl
    }


}
