package com.dhgrupo5.popfilm.pack.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dhgrupo5.popfilm.pack.model.Movie
import com.dhgrupo5.popfilm.pack.repository.MoviesAPIRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException
import com.dhgrupo5.popfilm.pack.model.Result


class MovieVM : ViewModel() {

    val listMutableMovie = MutableLiveData<List<Result>>()
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    private val repository = MoviesAPIRepository()

    init {
        getAllMovies()
    }

    fun getAllMovies() = CoroutineScope(Dispatchers.IO).launch {
        loading.postValue(true)
        try {
            repository.getMovieService().let { movieResponse ->
                listMutableMovie.postValue(movieResponse.moviesList)
                loading.postValue(false)
            }
        }catch (error: Throwable){
            loading.postValue(false)
            handleError(error)
        }
    }

    private fun handleError(error: Throwable) {
        when(error){
            is HttpException -> errorMessage.postValue("Erro de conexão código: ${error.code()}")
            is UnknownHostException -> errorMessage.postValue("Verifique sua conexão")
        }
    }
}
