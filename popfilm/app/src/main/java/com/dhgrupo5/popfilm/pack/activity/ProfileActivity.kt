package com.dhgrupo5.popfilm.pack.activity

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.dhgrupo5.popfilm.R
import com.google.android.material.snackbar.Snackbar


class ProfileActivity : AppCompatActivity() {


    private val imagem by lazy { findViewById<ImageView>(R.id.profilePhoto) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_screen)

        initViews()
    }

    private fun initViews() {
        val button = findViewById<Button>(R.id.update_button)

        button.setOnClickListener {
            Snackbar.make(button, "Perfil Atualizado!", Snackbar.LENGTH_LONG).show()

        }

    }

}

