package com.dhgrupo5.popfilm.pack.ui.activity.movies

import android.content.Context
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.pack.model.Genre
import com.dhgrupo5.popfilm.pack.model.Movie
import com.dhgrupo5.popfilm.pack.model.MovieResponse
import com.dhgrupo5.popfilm.pack.repository.MoviesAPIRepository
import com.dhgrupo5.popfilm.pack.ui.adapter.CategoryAdapter
import com.dhgrupo5.popfilm.pack.ui.adapter.MovieAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class DummyCategoryDetail : AppCompatActivity() {

    val toolbar by lazy { findViewById<Toolbar>(R.id.layout_too_tPadrao) }
    val recyclerView by lazy { findViewById<RecyclerView>(R.id.cat_rvCategoriasDetalhes) }
    val progressBar by lazy { findViewById<ProgressBar>(R.id.cat_pbCategoriasDetalhes) }
    val repository by lazy { MoviesAPIRepository() }

//    var movieID = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_detail)

        //settings
        settingToolbar()
        var listMovies = getListMovies()

        recyclerView.layoutManager =
            GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

//            var adapter = CategoryDetailAdapter(listMovies)

//            recyclerView.adapter = adapter

        var movieId: Int = intent.getIntExtra("id", 1)

        getListMovies()
//        findImage()

    }

    fun populateAdapter(listMoviesByCategory: MutableList<Movie>) {

        MainScope().launch {
            var adapter = MovieAdapter(listMoviesByCategory)
            recyclerView.adapter = adapter

        }

    }

    //settings
    fun settingToolbar() {
        toolbar.setTitle(getString(R.string.title_activity_categoria_detalhe))
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.toolbar_textcolor));

        setSupportActionBar(toolbar)
        var actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }

    fun getListMovies() {

        MainScope().launch {
            CoroutineScope(Dispatchers.Main).launch {
                val moviesByGenre = repository.getMovieService()

                populateAdapter(moviesByGenre.moviesList.toMutableList())
            }

        }
    }
}

//    fun findImage() : MovieResponse {
//        val moviePosterURL = movieID(POSTER_BASE_URL)
//        Glide.with(this)
//            .load(moviePosterURL)
//            .into(layout_list_cat_det_ivImage)
//    }


//    fun getListMovies():MutableList<Movie>?{
//
//        val code : Int = intent.getIntExtra("code", 0)
//        var title : String = intent.getStringExtra("title").toString();
//
//        updateTitleToolbar(title)
//
//        if(code != null && title != null){
//            //var list:MutableList<Movie> = Gson().fromJson(bundle, MutableList<Movie>)
//
//            var listMovies = mutableListOf<Movie>()
//            var titleCategory = title;
//            var codeCategory = code;
//            for (i in 1 .. 10 ){
//                listMovies.add(Movie(i, "Filme ${i}", "Filme para a categoria ${titleCategory}", "https://picsum.photos/800/600?random=${codeCategory}"))
//            }
//
//            return listMovies;
//        }
//
//        Toast.makeText(
//            this,
//            "Não foi possível obter a lista com os filmes! Dados não encontrados.",
//            Toast.LENGTH_SHORT
//        ).show()
//
//        return null;
//    }
//
//
//    //overrides
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        return super.onCreateOptionsMenu(menu)
//    }
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if(item.itemId == android.R.id.home){
//            finish()
//        }
//        return super.onOptionsItemSelected(item)
//    }
//    fun updateTitleToolbar(newTitle:String){
//        toolbar.setTitle(newTitle)
//    }

