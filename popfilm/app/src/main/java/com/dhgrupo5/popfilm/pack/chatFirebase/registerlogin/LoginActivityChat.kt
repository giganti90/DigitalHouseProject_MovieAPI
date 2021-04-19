package com.dhgrupo5.popfilm.registerlogin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.HorizontalScrollView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.messages.LatestMessagesActivity
import com.dhgrupo5.popfilm.pack.ui.activity.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login_chat.*

class LoginActivityChat: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_login_chat)

    login_button_login.setOnClickListener {
      performLogin()
    }

    back_to_register_textview.setOnClickListener{
     startActivity(Intent(this, RegisterActivityChat::class.java))
      finish()
    }
  }

  private fun performLogin() {
    val email = email_edittext_login.text.toString()
    val password = password_edittext_login.text.toString()

    if (email.isEmpty() || password.isEmpty()) {
      Toast.makeText(this, "Please fill out email/pw.", Toast.LENGTH_SHORT).show()
      return
    }

    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
        .addOnCompleteListener {
          if (!it.isSuccessful) return@addOnCompleteListener

          Log.d("Login", "Successfully logged in: ${it.result?.user?.uid}")

          val intent = Intent(this, HomeActivity::class.java)
          intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
          startActivity(intent)
        }
        .addOnFailureListener {
          Toast.makeText(this, "Failed to log in: ${it.message}", Toast.LENGTH_SHORT).show()
        }
  }

}