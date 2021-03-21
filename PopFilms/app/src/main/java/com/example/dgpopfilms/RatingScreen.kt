package com.example.dgpopfilms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.dgpopfilms.R.id.ratingBar
import com.google.android.material.snackbar.Snackbar

class RatingScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating_screen)

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
                this, "Avaliado em " + rBar,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
