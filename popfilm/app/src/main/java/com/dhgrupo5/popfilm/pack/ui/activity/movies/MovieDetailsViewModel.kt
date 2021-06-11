package com.dhgrupo5.popfilm.pack.ui.activity.movies

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dhgrupo5.popfilm.pack.model.GenreDCModelForCategories
import com.dhgrupo5.popfilm.pack.model.MovieResponse
import com.dhgrupo5.popfilm.pack.repository.MoviesAPIRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException

class MovieDetailsViewModel: ViewModel() {

    private val _film = MutableLiveData<List<MovieResponse>>()
    val film: LiveData<List<MovieResponse>> = _film
    var movieId = 0
    val loading = MutableLiveData<Boolean>()
    private val repository = MoviesAPIRepository()
    val trailerLiveData = MutableLiveData<String>()
    val errorMessage = MutableLiveData<String>()


    private val moviesRepository = MoviesAPIRepository()
    private fun getTrailer()= CoroutineScope(Dispatchers.IO).launch {
        loading.postValue(true)
        try {
            repository.getMovieTrailer(movieId).let { trailer ->
                for (it in trailer.results){
                    if(it.type == "Trailer"){
                        trailerLiveData.postValue(it.key)
                        break
                    }
                }
            }
        }
        catch (error: Throwable){
            loading.postValue(false)
            handleError(error)
        }
    }
    fun configMovieID(id: Int){
        movieId = id
        getTrailer()
    }
    private fun handleError(error: Throwable) {
        when(error){
            is HttpException -> errorMessage.postValue("Erro de conexão código: ${error.code()}")
            is UnknownHostException -> errorMessage.postValue("Verifique sua conexão")
        }
    }


}
