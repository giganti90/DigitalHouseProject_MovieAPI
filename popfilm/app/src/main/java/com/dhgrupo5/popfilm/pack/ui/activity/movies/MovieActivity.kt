package com.dhgrupo5.popfilm.pack.ui.activity.movies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.pack.model.Movie
import com.dhgrupo5.popfilm.pack.ui.adapter.MovieAdapter

class MovieActivity : AppCompatActivity() {

    val toolbar by lazy { findViewById<Toolbar>(R.id.layout_too_tPadrao) }
    val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerview) }
    val progressBar by lazy { findViewById<ProgressBar>(R.id.progress) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        //settings
        settingToolbar()

        var listMovies = getListMovies();

        if (listMovies != null){
            //recyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
            recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            var adapter = MovieAdapter(listMovies)
            recyclerView.adapter = adapter
        }

    }


    //setttings
    fun settingToolbar(){
        toolbar.setTitle(getString(R.string.title_activity_movie))
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.toolbar_textcolor))
        setSupportActionBar(toolbar)
        var actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }
    fun getListMovies():MutableList<Movie>{
        var list = mutableListOf<Movie>()

        for (i in 1 .. 20 ){
            list.add(Movie())
        }

        return list
    }


    //overrides
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item)
    }

}