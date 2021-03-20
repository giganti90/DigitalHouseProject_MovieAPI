package com.example.dgpopfilms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.Toast
import com.example.dgpopfilms.R.id.ratingBar

class RatingScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating_screen)


                // Create RatingBar
                val rBar = RatingBar(this)
                val layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
                rBar.layoutParams = layoutParams
                rBar.stepSize = 1.0.toFloat()
                rBar.numStars = 5

                //create button
                val button = Button(this)
                val layoutParams1 = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
                button.text="Submit Rating"


                val linearLayout = findViewById<LinearLayout>(R.id.container)
                // Add RatingBar and button to LinearLayout
                linearLayout?.addView(rBar)
                linearLayout?.addView(button)

                button?.setOnClickListener {
                    val msg = rBar.rating.toString()
                    Toast.makeText(this, "Given Rating: "+msg,
                        Toast.LENGTH_SHORT).show()
                }
            }
        }

//        val rating = findViewById<RatingBar>(ratingBar)
//        if (rating != null) {
//            val button = findViewById<Button>(R.id.button)
//            button?.setOnClickListener {
//                val msg = rating.rating.toString()
//                Toast.makeText(
//                    this,
//                    "Rating is: " + msg, Toast.LENGTH_SHORT
//                ).show()
//            }
//        }
