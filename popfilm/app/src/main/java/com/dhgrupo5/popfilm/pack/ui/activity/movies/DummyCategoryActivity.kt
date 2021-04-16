package com.dhgrupo5.popfilm.pack.ui.activity.movies

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.pack.model.Category
import com.dhgrupo5.popfilm.pack.model.Genre
import com.dhgrupo5.popfilm.pack.model.Movie
import com.dhgrupo5.popfilm.pack.repository.MoviesAPIRepository
import com.dhgrupo5.popfilm.pack.ui.adapter.CategoryAdapter
import kotlinx.coroutines.*

class DummyCategoryActivity : AppCompatActivity() {
    val recycler by lazy { findViewById<RecyclerView>(R.id.cat_rvCategorias) }
    val toolbar by lazy { findViewById<Toolbar>(R.id.layout_too_tPadrao) }
    val progress by lazy { findViewById<ProgressBar>(R.id.cat_pbCategorias) }
    val repository by lazy { MoviesAPIRepository() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        //settings
        settingToolbar();

        //recycler.layoutManager = LinearLayoutManager(this)
        recycler.layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)

//        var adapter = CategoryAdapter(listCategories)

//        recycler.adapter = adapter;

        getCategories()

    }



    fun populateAdapter(listCategories: MutableList<Genre>){

        MainScope().launch {
            var adapter = CategoryAdapter(listCategories)
            recycler.adapter = adapter

        }

    }

    fun settingToolbar(){
        toolbar.setTitle(getString(R.string.title_activity_categoria))
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.toolbar_textcolor));

        setSupportActionBar(toolbar)
        var actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }


    fun getCategories() {

        MainScope().launch {
            CoroutineScope(Dispatchers.Main).launch {
                val genres = repository.getMovieGenre()

                populateAdapter(genres.genres.toMutableList())
            }
        }

//        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//            return super.onCreateOptionsMenu(menu)
//        }
//
//        override fun onOptionsItemSelected(item: MenuItem): Boolean {
//            if (item.itemId == android.R.id.home) {
//                finish()
//            }
//            return super.onOptionsItemSelected(item)
//        }

    }

}

//        var listCategories = mutableListOf<Genre>()
//
//        //categoria 1
//        var listMovies = mutableListOf<Movie>()
//        var titleCategory: List<Genre>
//        var codeCategory = id.Genre
//        for (i in 1 .. 10 ){
//            listMovies.add(Movie(i, "Filme ${i}", "Filme para a categoria ${titleCategory}", "https://picsum.photos/800/600?random=${codeCategory}"))
//        }
//        listCategories.add(Genre(codeCategory, titleCategory, "https://picsum.photos/800/600?random=${codeCategory}", listMovies))
//
//        //categoria 2
//        listMovies = mutableListOf<Movie>()
//        titleCategory = "Suspense";
//        codeCategory = 2;
//        for (i in 1 .. 5 ){
//            listMovies.add(Movie(i, "Filme ${i}", "Filme para a categoria ${titleCategory}", "https://picsum.photos/800/600?random=${codeCategory}"))
//        }
//        listCategories.add(Genre(codeCategory, titleCategory, "https://picsum.photos/800/600?random=${codeCategory}", listMovies))
//
//
////        listCategories.add(Category(1, "Ação", "https://picsum.photos/800/600?random=1"))
////        listCategories.add(Category(2, "Suspense", "https://picsum.photos/800/600?random=2"))
//        listCategories.add(Category(3, "Romance", "https://picsum.photos/800/600?random=3", listMovies))
//        listCategories.add(Category(4, "Comédia", "https://picsum.photos/800/600?random=4", listMovies))
//        listCategories.add(Category(5, "Brasileiro", "https://picsum.photos/800/600?random=5", listMovies))
//        listCategories.add(Category(6, "Kids", "https://picsum.photos/800/600?random=6", listMovies))
//        listCategories.add(Category(7, "Terror", "https://picsum.photos/800/600?random=7", listMovies))
//        listCategories.add(Category(8, "Guerra", "https://picsum.photos/800/600?random=8", listMovies))
//        listCategories.add(Category(9, "Super Herói", "https://picsum.photos/800/600?random=9", listMovies))
//        listCategories.add(Category(10, "Categoria 1", "https://picsum.photos/800/600?random=10", listMovies))
//        listCategories.add(Category(10, "Categoria 1", "https://picsum.photos/800/600?random=11", listMovies))
//        listCategories.add(Category(11, "Categoria 2", "https://picsum.photos/800/600?random=12", listMovies))
//        listCategories.add(Category(12, "Categoria 3", "https://picsum.photos/800/600?random=13", listMovies))
//        listCategories.add(Category(13, "Categoria 4", "https://picsum.photos/800/600?random=14", listMovies))
//        listCategories.add(Category(14, "Categoria 5", "https://picsum.photos/800/600?random=15", listMovies))
//        listCategories.add(Category(15, "Categoria 6", "https://picsum.photos/800/600?random=16", listMovies))
//        listCategories.add(Category(16, "Categoria 7", "https://picsum.photos/800/600?random=17", listMovies))
//        listCategories.add(Category(17, "Categoria 8", "https://picsum.photos/800/600?random=18", listMovies))
//        listCategories.add(Category(18, "Categoria 9", "https://picsum.photos/800/600?random=19", listMovies))
//        return listCategories;
//    }
