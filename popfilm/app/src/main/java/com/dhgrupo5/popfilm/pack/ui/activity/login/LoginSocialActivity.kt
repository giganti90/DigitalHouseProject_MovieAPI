package com.dhgrupo5.popfilm.pack.ui.activity.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.pack.model.tmdb.auth.GuestSession
import com.dhgrupo5.popfilm.pack.ui.activity.HomeActivity
import com.dhgrupo5.popfilm.pack.ui.viewmodel.LoginSocialViewModel

class LoginSocialActivity : AppCompatActivity() {
    private val googleButton by lazy { findViewById<Button>(R.id.login_social_google_btn) }
    private val facebookButton by lazy { findViewById<Button>(R.id.login_social_facebook_btn) }
    private val emailButton by lazy { findViewById<Button>(R.id.login_social_email_btn) }
    private val signupButton by lazy { findViewById<Button>(R.id.login_social_signup_btn) }
    private val ignoreButton by lazy { findViewById<Button>(R.id.login_social_ignore_btn) }

    private lateinit var viewModel: LoginSocialViewModel
    private var guestSession = GuestSession()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginSocialViewModel::class.java)
        viewModel.getGuestSession()
        viewModel.guestSession.observe(this, { _guestSession ->
            guestSession = _guestSession
        })


        setContentView(R.layout.activity_login_social)
        val todoToast = Toast.makeText(this, "Under construction", Toast.LENGTH_SHORT)
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
            val intent = Intent(this, LoginSignupActivity::class.java)
            startActivity(intent)
        }
        ignoreButton.setOnClickListener() {
            startActivity(
                    Intent(this, HomeActivity::class.java)
            )
        }
    }
}