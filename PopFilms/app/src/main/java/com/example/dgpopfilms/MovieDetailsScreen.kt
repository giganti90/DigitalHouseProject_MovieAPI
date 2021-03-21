package com.example.dgpopfilms

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MovieDetailsScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val ratingButton = findViewById<ImageButton>(R.id.rate)
        ratingButton.setOnClickListener {
            val intent = Intent(this, RatingScreen::class.java)
            startActivity(intent)
        }

//        val trailerButton = findViewById<ImageButton>(R.id.play_trailer)
//        trailerButton.setOnClickListener {
//            val intent = Intent(this, RatingScreen::class.java)
//            startActivity(intent)
//        }
//
//        val whereButton = findViewById<ImageButton>(R.id.where_to_watch)
//        whereButton.setOnClickListener {
//            val intent = Intent(this, RatingScreen::class.java)
//            startActivity(intent)
//        }


    }
}
