package com.dhgrupo5.popfilm.pack.ui.activity.movies

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.pack.model.Genre
import com.dhgrupo5.popfilm.pack.model.GenresResponse
import com.dhgrupo5.popfilm.pack.model.Movie
import com.dhgrupo5.popfilm.pack.ui.MovieViewHolder
import com.dhgrupo5.popfilm.pack.ui.adapter.MovieAdapter
import com.dhgrupo5.popfilm.pack.ui.viewmodel.MovieVM

class DummyCategoryDetail : AppCompatActivity() {
    private val recyclerViewCategoria by lazy { findViewById<RecyclerView>(R.id.cat_rvCategoriasDetalhes) }
    private val toolbar by lazy { findViewById<Toolbar>(R.id.cat_pbCategoriasDetalhes) }
    private val clickImagem by lazy { findViewById<ImageView>(R.id.layout_lista_cat_ivImage) }

    private lateinit var viewModel: MovieVM
    var listGenres = mutableListOf<Genre>()
    var listReleaseMovies = mutableListOf<Movie>()
    val adapterMovie = MovieAdapter(listReleaseMovies)
    val adapterGenre = GenresResponse(listGenres)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_list_category_detail)
        viewModel = ViewModelProviders.of(this).get(MovieVM::class.java)
        viewModel.getMovieGenre()

        observers()
        setupRecyclerView()
        initClick()
    }

    private fun observers() {
        viewModel.movieList.observe(this, Observer {
            it?.let {
                listReleaseMovies.addAll(it)
                adapterMovie.notifyDataSetChanged()
            }
        })
        viewModel.listGenre.observe(this, Observer {
            it?.let {
                listGenres.addAll(it)
            }
        })
    }

    private fun setupRecyclerView() {

        recyclerViewCategoria.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = MovieAdapter(listReleaseMovies)
        recyclerViewCategoria.adapter = adapter
    }

    private fun initClick() {

        clickImagem.setOnClickListener {
            val intent = Intent(this, MovieDetailsActivity::class.java)
            startActivity(intent)
        }
    }
}
