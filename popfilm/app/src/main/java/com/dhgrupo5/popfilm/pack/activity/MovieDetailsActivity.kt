package com.dhgrupo5.popfilm.pack.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.pack.RatingActivity

class MovieDetailsActivity : AppCompatActivity() {

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

        val trailerButton = findViewById<ImageButton>(R.id.rate)
        ratingButton.setOnClickListener {
            val intent = Intent(this, RatingActivity::class.java)
            startActivity(intent)
        }
    }
}
