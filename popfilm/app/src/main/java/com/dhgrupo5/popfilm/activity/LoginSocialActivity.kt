package com.dhgrupo5.popfilm.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.dhgrupo5.popfilm.R

class LoginSocialActivity : AppCompatActivity() {
    val googleButton by lazy { findViewById<Button>(R.id.login_social_google_btn) }
    val facebookButton by lazy { findViewById<Button>(R.id.login_social_facebook_btn) }
    val emailButton by lazy { findViewById<Button>(R.id.login_social_email_btn) }
    val signupButton by lazy { findViewById<Button>(R.id.login_social_signup_btn) }
    val ignoreButton by lazy { findViewById<Button>(R.id.login_social_ignore_btn) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_social)
        val todoToast = Toast.makeText(this,"Under construction",Toast.LENGTH_LONG)
        googleButton.setOnClickListener() {
            todoToast.show()
        }
        facebookButton.setOnClickListener() {
            todoToast.show()
        }
        emailButton.setOnClickListener() {
            val intent = Intent(this, LoginEmailActivity::class.java)
            startActivity(intent)
        }
        signupButton.setOnClickListener() {
            todoToast.show()
        }
        ignoreButton.setOnClickListener() {
            todoToast.show()
        }
    }
}