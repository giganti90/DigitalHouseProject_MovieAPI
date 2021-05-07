package com.dhgrupo5.popfilm.pack.ui.activity.movies

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.pack.model.DiscoverResponse
import com.dhgrupo5.popfilm.pack.model.Movie
import com.dhgrupo5.popfilm.pack.repository.MoviesAPIRepository
import com.dhgrupo5.popfilm.pack.ui.adapter.CategoryDetailAdapter
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
        recycler.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

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
            var adapter = CategoryDetailAdapter(discover.movies.toMutableList())
            recycler.adapter = adapter


            Toast.makeText(
                    this@CategoryDetailActivity,
                    "Primeiro filme é:\n${discover.movies[0].title}",
                    Toast.LENGTH_SHORT
            ).show()
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

    //overrides
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

