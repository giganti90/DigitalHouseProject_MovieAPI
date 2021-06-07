package com.dhgrupo5.popfilm.pack.ui.activity.movies

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.pack.model.DiscoverResponse
import com.dhgrupo5.popfilm.pack.repository.MoviesAPIRepository
import com.dhgrupo5.popfilm.pack.ui.adapter.CategoryInfoAdapterForCategories
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class CategoryDetailActivity : AppCompatActivity() {
    private val recycler by lazy { findViewById<RecyclerView>(R.id.cat_rvCategoriasDetalhes) }
    private val toolbar by lazy { findViewById<Toolbar>(R.id.layout_too_tPadrao) }
    val repository by lazy { MoviesAPIRepository() }

    val genreID by lazy { intent?.extras?.getString("id") ?: throw IllegalStateException() }
    val title by lazy { intent?.extras?.getString("title") ?: throw IllegalStateException() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_detail)


        settingToolbar()
        recycler.layoutManager = LinearLayoutManager(this);
//        recycler.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

        getMoviesFromGenre()

    }



    fun settingToolbar(){
        toolbar.setTitle(title)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.toolbar_textcolor));

        setSupportActionBar(toolbar)
        var actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }

    fun populateAdapter(discover: DiscoverResponse){

        MainScope().launch {
            var adapter = CategoryInfoAdapterForCategories(discover.movies.toMutableList())
            recycler.adapter = adapter

        }

    }


    fun getMoviesFromGenre(){
        MainScope().launch {
            CoroutineScope(Dispatchers.Main).launch {
                val movieResponse = repository.getMoviesByGenre(genreID)

                populateAdapter(movieResponse)
            }
        }
    }

}

