package com.dhgrupo5.popfilm.pack.ui.activity.movies

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.pack.model.MovieResponse
import com.dhgrupo5.popfilm.pack.repository.MoviesAPIRepository
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.dhgrupo5.popfilm.pack.ui.adapter.CategoryInfoAdapterForCategories
import com.dhgrupo5.popfilm.pack.utils.moviesdb.NetworkUtils
import com.squareup.picasso.Picasso

class MovieDetailsActivity : AppCompatActivity() {

//    private val toolbar = findViewById<Toolbar>(R.id.layout_too_tPadrao)
    val repository = MoviesAPIRepository()

    private lateinit var movieResponse: MovieResponse

    val toolbar by lazy { findViewById<Toolbar>(R.id.layout_too_tPadrao) }
    val title by lazy { intent?.extras?.getString("title") ?: throw IllegalStateException() }


    val name: TextView by lazy { findViewById(R.id.moviedetails_name) }
    val image: ImageView by lazy { findViewById(R.id.moviedetails_poster) }
    val synopsis: TextView by lazy { findViewById(R.id.moviedetails_synopsis) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        settingToolbar()


        val informacoes = intent.extras

        if (informacoes != null) {
            movieResponse = informacoes.getSerializable("movie") as MovieResponse
            name.text = movieResponse.title
            val url = "${NetworkUtils.IMG_BASE_URL}w500${movieResponse.posterPath}"
            Picasso.get().load(url).into(image)
            synopsis.text = movieResponse.overview.toString()
        }



        val ratingButton = findViewById<ImageButton>(R.id.rate)
        ratingButton.setOnClickListener {
            val intent = Intent(this, RatingActivity::class.java)
            movieResponse.let {
                intent.putExtra("movie", movieResponse)
            }
            startActivity(intent)
        }


        val trailerButton = findViewById<ImageButton>(R.id.play_trailer)
        trailerButton.setOnClickListener {
            val intent = Intent(this, MoviePlayActivity::class.java)
            startActivity(intent)
        }

    }





    fun settingToolbar() {
//        toolbar.setTitle(title)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.toolbar_textcolor));

        setSupportActionBar(toolbar)
        var actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }

//        fun populateAdapter(discover: DiscoverResponse) : CategoryInfoAdapterForCategories {
//            var movieOverview = CategoryInfoAdapterForCategories()
//
//            MainScope().launch {
//                movieOverview = CategoryInfoAdapterForCategories(discover.movies.toMutableList())
//
//            }
//            return movieOverview
//        }
}
