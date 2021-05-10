package com.dhgrupo5.popfilm.pack.ui.activity.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.pack.model.tmdb.auth.GuestSession
import com.dhgrupo5.popfilm.pack.ui.activity.HomeActivity
import com.dhgrupo5.popfilm.pack.ui.viewmodel.LoginSocialViewModel
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import java.security.Provider

@Suppress("DEPRECATION")
class LoginSocialActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {

    private val facebookButton by lazy { findViewById<Button>(R.id.login_button) }
    private val emailButton by lazy { findViewById<Button>(R.id.login_social_email_btn) }
    private val signupButton by lazy { findViewById<Button>(R.id.login_social_signup_btn) }
    private lateinit var viewModel: LoginSocialViewModel
    private var guestSession = GuestSession()

    //LoginGoogle ----------------------------------------------------------------------------------
    private lateinit var googleApiClient: GoogleApiClient
    private lateinit var signInButton: SignInButton
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseAuthListener: FirebaseAuth.AuthStateListener
    private lateinit var progressBar: ProgressBar

    companion object {
        const val SIGN_IN_CODE = 777
    }

    //Login Facebook -------------------------------------------------------------------------------
    private val callbackManager = CallbackManager. Factory. create()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_social)

        viewModel = ViewModelProvider(this).get(LoginSocialViewModel::class.java)
        viewModel.getGuestSession()
        viewModel.guestSession.observe(this, Observer { _guestSession ->
            guestSession = _guestSession
        })

        emailButton.setOnClickListener() {
            val intent = Intent(this, LoginEmailActivity::class.java)
            startActivity(intent)
        }
        signupButton.setOnClickListener() {
            val intent = Intent(this, LoginSignupActivity::class.java)
            startActivity(intent)
        }

        // LoginGoogle
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleApiClient = GoogleApiClient.Builder(this)
            .enableAutoManage(this, this)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build()
        signInButton = findViewById(R.id.login_social_google_btn) as SignInButton
        signInButton!!.setSize(SignInButton.SIZE_WIDE)
        signInButton!!.setColorScheme(SignInButton.COLOR_DARK)
        signInButton!!.setOnClickListener {
            val intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
            startActivityForResult(intent, LoginSocialActivity.SIGN_IN_CODE)
        }
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuthListener =
            FirebaseAuth.AuthStateListener { firebaseAuth ->
                val user = firebaseAuth.currentUser
                if (user != null) {
                    goMainScreen()
                }
            }
        progressBar = findViewById(R.id.progressBar)

        //Login Facebook ---------------------------------------------------------------------------
        facebookButton.setOnClickListener{
            LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"))
            LoginManager.getInstance().registerCallback(callbackManager,
                    object : FacebookCallback<LoginResult>{
                        override fun onSuccess(loginResult: LoginResult?) {
                            loginResult?.let {
                                val token = it.accessToken

                                handleFacebookAccessToken(loginResult.accessToken)

                                val credential = FacebookAuthProvider.getCredential(token.token)

                                FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                                    if (it.isSuccessful){
                                    }else{
                                    }
                                }
                            }

                        }
                        override fun onCancel() {

                        }
                        override fun onError(error: FacebookException?) {

                        }

                    } )
        }


    }
    //LoginFacebook
    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d("facebook", "handleFacebookAccessToken:$token")

        startActivity(
            Intent(this, HomeActivity::class.java)
        )

        finish()
    }

    override fun onStart() {
        super.onStart()
        firebaseAuth!!.addAuthStateListener(firebaseAuthListener!!)
    }

    override fun onConnectionFailed(@NonNull connectionResult: ConnectionResult) {}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        //Login Facebook
        callbackManager.onActivityResult(requestCode, resultCode, data)

        //LoginGoogle
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SIGN_IN_CODE) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleSignInResult(result)
        }
    }

    private fun handleSignInResult(result: GoogleSignInResult?) {
        if (result!!.isSuccess) {
            firebaseAuthWithGoogle(result.signInAccount)
        } else {
            Toast.makeText(this, R.string.not_log_in, Toast.LENGTH_SHORT).show()
        }
    }

    private fun firebaseAuthWithGoogle(signInAccount: GoogleSignInAccount?) {
        progressBar.visibility = View.VISIBLE
        signInButton.visibility = View.GONE
        val credential = GoogleAuthProvider.getCredential(signInAccount!!.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(
            this
        ) { task ->
            progressBar!!.visibility = View.GONE
            signInButton!!.visibility = View.VISIBLE
            if (!task.isSuccessful) {
                Toast.makeText(applicationContext, R.string.not_firebase_auth, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun goMainScreen() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()
        firebaseAuth.removeAuthStateListener(firebaseAuthListener)
    }
}