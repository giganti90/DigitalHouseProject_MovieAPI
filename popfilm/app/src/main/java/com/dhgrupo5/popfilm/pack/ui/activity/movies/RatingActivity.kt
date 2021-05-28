package com.dhgrupo5.popfilm.pack.ui.activity.movies

import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.pack.repository.FirebaseRepository
import com.google.android.material.snackbar.Snackbar

class RatingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)

        initViews()
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
            val msg = rBar.rating.toString()
            Toast.makeText(
                    this, "Avaliado em " + msg,
                    Toast.LENGTH_SHORT
            ).show()
            FirebaseRepository().newRating(msg)
        }
    }
}
