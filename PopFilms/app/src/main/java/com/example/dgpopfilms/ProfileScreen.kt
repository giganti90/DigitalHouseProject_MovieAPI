package com.example.dgpopfilms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class ProfileScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_screen)
    }
    initViews()


private fun initViews() {
    val floatingActionButton = findViewById<FloatingActionButton>(R.id.botao)

    floatingActionButton.setOnClickListener {

        val texto1 = primeiro_texto.text.toString()
        val texto2 = segundo_texto.text.toString()

        if (texto1.isEmpty() || texto2.isEmpty()) {
            Log.d("input", "texto invalido")
            Snackbar.make(floatingActionButton, "texto invalido", Snackbar.LENGTH_LONG).show()
            Toast.makeText(this, "um ou mais campos estão vazios", Toast.LENGTH_LONG).show()
        } else {
            Snackbar.make(floatingActionButton, "obrigado", Snackbar.LENGTH_INDEFINITE)
                .setAction("fechar")
                {
                    Toast.makeText(this, "tudo certo!", Toast.LENGTH_LONG).show()
                }
                .show()
        }
    }

//        botao.setOnClickListener {
//            val intent = Intent(this, SecondActivity::class.java)
//
//            startActivity(intent)
//        }
        }
     }
