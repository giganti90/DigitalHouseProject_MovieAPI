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
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class MovieDetailsActivity : AppCompatActivity() {

    private val toolbar = findViewById<Toolbar>(R.id.layout_too_tPadrao)
    val repository = MoviesAPIRepository()

    val name = findViewById<TextView>(R.id.movie_name)
    val imagem = findViewById<ImageView>(R.id.profilePhoto)
    val synopsis = findViewById<TextView>(R.id.movie_details)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

//        settingToolbar()


        val informacoes = intent.extras

        if (informacoes != null) {
            val films = informacoes.getSerializable("id") as MovieResponse
            name.text = films.title
            Picasso.get().load(films.url).into(imagem)
            synopsis.text = films.overview.toString()
        }


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


}
