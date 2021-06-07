package com.dhgrupo5.popfilm.pack.ui.activity.movies

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.pack.model.MovieResponse
import com.dhgrupo5.popfilm.pack.repository.FirebaseRepository
import com.dhgrupo5.popfilm.pack.utils.moviesdb.NetworkUtils
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class RatingActivity : AppCompatActivity() {

    val movie by lazy { intent?.extras?.getSerializable("movie") as MovieResponse }

    val name: TextView by lazy { findViewById(R.id.ratings_moviename)}
    val image: ImageView by lazy { findViewById(R.id.rating_poster) }
    val synopsis: TextView by lazy { findViewById(R.id.rating_synopsis)}
    val toolbar by lazy { findViewById<Toolbar>(R.id.layout_too_tPadrao) }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)

        initViews()
        settingToolbar()

        name.text = movie.title
        val url = "${NetworkUtils.IMG_BASE_URL}w500${movie.posterPath}"
        Picasso.get().load(url).into(image)
        synopsis.text = movie.overview.toString()

    }



    private fun initViews() {
        val updatebutton = findViewById<Button>(R.id.button)

        updatebutton.setOnClickListener {

            Snackbar.make(updatebutton, "obrigado", Snackbar.LENGTH_INDEFINITE)
                .setAction("fechar")
                {
                    Toast.makeText(this, "tudo certo!", Toast.LENGTH_LONG).show()
                }
                .show()
        }


        // Create RatingBar
        val rBar = findViewById<RatingBar>(R.id.ratingBar)

        updatebutton?.setOnClickListener {
            val msg = "Avaliado em ${rBar.rating}"
            Toast.makeText(
                    this, msg,
                    Toast.LENGTH_SHORT
            ).show()
            FirebaseRepository().newRating(movie.title, rBar.rating)
        }
    }

    fun settingToolbar() {
//        toolbar.setTitle(title)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.toolbar_textcolor));

        setSupportActionBar(toolbar)
        var actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }

}
