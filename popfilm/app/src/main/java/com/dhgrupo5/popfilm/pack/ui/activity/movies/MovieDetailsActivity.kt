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
import com.dhgrupo5.popfilm.pack.ui.adapter.CategoryInfoAdapterForCategories
import com.squareup.picasso.Picasso

class MovieDetailsActivity : AppCompatActivity() {

//    private val toolbar = findViewById<Toolbar>(R.id.layout_too_tPadrao)
    val repository = MoviesAPIRepository()

    private lateinit var movieResponse: MovieResponse

    private val viewmodel: MovieDetailsViewModel by viewModels()
    private lateinit var adapter: CategoryInfoAdapterForCategories
    private var films = mutableListOf<MovieResponse>()


    val name: TextView by lazy { findViewById(R.id.moviedetails_name) }
    val image: ImageView by lazy { findViewById(R.id.moviedetails_poster) }
    val synopsis: TextView by lazy { findViewById(R.id.rating_synopsis) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

//        settingToolbar()


        val informacoes = intent.extras

        if (informacoes != null) {
            movieResponse = informacoes.getSerializable("movie") as MovieResponse
            name.text = movieResponse.title
            Picasso.get().load(movieResponse.url).into(image)
            synopsis.text = movieResponse.overview.toString()
        }

//        adapter = CategoryInfoAdapterForCategories(films)
//        viewmodel.getGenres()
//        viewmodel.genres.observe( this, {list ->
//            films.addAll()
//        } )


        val ratingButton = findViewById<ImageButton>(R.id.rate)
        ratingButton.setOnClickListener {
            val intent = Intent(this, RatingActivity::class.java)
                .putExtra("title",title)
            startActivity(intent)
        }


        val trailerButton = findViewById<ImageButton>(R.id.play_trailer)
        trailerButton.setOnClickListener {
            val intent = Intent(this, MoviePlayActivity::class.java)
            startActivity(intent)
        }

    }





//        fun settingToolbar(){
//            toolbar.setTitle(title)
//            toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.toolbar_textcolor));
//
//            setSupportActionBar(toolbar)
//            var actionbar = supportActionBar
//            actionbar?.setDisplayHomeAsUpEnabled(true)
//        }

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
