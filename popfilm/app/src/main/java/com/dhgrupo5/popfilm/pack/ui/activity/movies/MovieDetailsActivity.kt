package com.dhgrupo5.popfilm.pack.ui.activity.movies

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.pack.model.DiscoverResponse
import com.dhgrupo5.popfilm.pack.model.MovieResponse
import com.dhgrupo5.popfilm.pack.repository.MoviesAPIRepository
import com.dhgrupo5.popfilm.pack.ui.adapter.MovieAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MovieDetailsActivity : AppCompatActivity() {

    private val toolbar by lazy { findViewById<Toolbar>(R.id.layout_too_tPadrao) }
    val movieID by lazy { intent?.extras?.getString("id") ?: throw IllegalStateException() }
    val overview by lazy { intent?.extras?.getString("overview") ?: throw IllegalStateException() }
    val repository by lazy { MoviesAPIRepository() }

    val title by lazy { intent?.extras?.getString("title") }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        settingToolbar()
//        getMovieDetails()


        val ratingButton = findViewById<ImageButton>(R.id.rate)
        ratingButton.setOnClickListener {
            val intent = Intent(this, RatingActivity::class.java)
            startActivity(intent)
        }

        val whereButton = findViewById<ImageButton>(R.id.where_to_watch)
        ratingButton.setOnClickListener {
            val intent = Intent(this, RatingActivity::class.java)
            startActivity(intent)

        }

        val trailerButton = findViewById<ImageButton>(R.id.play_trailer)
        ratingButton.setOnClickListener {
            val intent = Intent(this, RatingActivity::class.java)
            startActivity(intent)
        }

        Toast.makeText(
                this,
                "Você clicou no filme:\n${movieID}",
                Toast.LENGTH_SHORT
        ).show()
    }





        fun settingToolbar(){
            toolbar.setTitle(title)
            toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.toolbar_textcolor));

            setSupportActionBar(toolbar)
            var actionbar = supportActionBar
            actionbar?.setDisplayHomeAsUpEnabled(true)
        }

//        fun populateAdapter(discover: DiscoverResponse){
//
//            MainScope().launch {
//                var movieOverview = MovieAdapter(discover.movies.toMutableList())
//                var movieTitle = MovieAdapter(discover.id)

//                Toast.makeText(
//                        this@MovieDetailsActivity,
//                        "Nome do filme é:\n${discover.title[0]}",
//                        Toast.LENGTH_SHORT
//                ).show()
//            }





//        fun getMovieDetails(){
//            MainScope().launch {
//                CoroutineScope(Dispatchers.Main).launch {
//                    val overviewResponse = repository.getMovieDetails(overview) //movieID?
//
//                    populateAdapter(overviewResponse)
//                }
//            }
//        }
}



