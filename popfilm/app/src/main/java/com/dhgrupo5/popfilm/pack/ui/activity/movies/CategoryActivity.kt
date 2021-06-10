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
import com.dhgrupo5.popfilm.pack.model.GenreDCModelForCategories
import com.dhgrupo5.popfilm.pack.repository.MoviesAPIRepository
import com.dhgrupo5.popfilm.pack.ui.adapter.CategoryAdapterForGenres
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class CategoryActivity : AppCompatActivity() {
    val recycler by lazy { findViewById<RecyclerView>(R.id.cat_rvCategorias) }
    val toolbar by lazy { findViewById<Toolbar>(R.id.layout_too_tPadrao) }
    val progress by lazy { findViewById<ProgressBar>(R.id.cat_pbCategorias) }
    val repository by lazy { MoviesAPIRepository() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        //settings
        settingToolbar();
        recycler.layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)

        getCategories()

    }


    fun populateAdapter(listCategories: MutableList<GenreDCModelForCategories>) {

        MainScope().launch {
            var adapter = CategoryAdapterForGenres(listCategories)
            recycler.adapter = adapter

        }

    }

    fun settingToolbar() {
        toolbar.setTitle(getString(R.string.title_activity_categoria))
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.toolbar_textcolor));

        setSupportActionBar(toolbar)
        var actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }


    fun getCategories() {

        MainScope().launch {
            CoroutineScope(Dispatchers.Main).launch {
                val genres = repository.getGenres().genres
                genres?.forEach { genre ->
                    genre.movies = repository.getMoviesByGenre(genre.id).movies.toMutableList()
                }

                if (genres != null) {
                    populateAdapter(genres.toMutableList())
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
