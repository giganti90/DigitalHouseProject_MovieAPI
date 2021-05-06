package com.dhgrupo5.popfilm.pack.ui.activity.movies

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dhgrupo5.popfilm.R

class MovieDetailsActivity : AppCompatActivity() {

    val movieID by lazy { intent?.extras?.getString("id") ?: throw IllegalStateException() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val ratingButton = findViewById<ImageButton>(R.id.rate)
        ratingButton.setOnClickListener {
            val intent = Intent(this, RatingActivity::class.java)
            startActivity(intent)
        }

        val whereButton = findViewById<ImageButton>(R.id.rate)
        ratingButton.setOnClickListener {
            val intent = Intent(this, RatingActivity::class.java)
            startActivity(intent)

        }

        val trailerButton = findViewById<ImageButton>(R.id.play_trailer)
        trailerButton.setOnClickListener {
            startActivity(
                Intent(this, MoviePlayActivity::class.java)
            )
        }

        Toast.makeText(
                this,
                "Você clicou no filme:\n${movieID}",
                Toast.LENGTH_SHORT
        ).show()

    }
}
