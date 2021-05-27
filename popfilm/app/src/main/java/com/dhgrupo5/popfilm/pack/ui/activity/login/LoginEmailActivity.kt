package com.dhgrupo5.popfilm.pack.ui.activity.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.pack.repository.FirebaseRepository
import com.dhgrupo5.popfilm.pack.ui.activity.HomeActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth


class LoginEmailActivity : AppCompatActivity() {

    val emailTextLayout by lazy { findViewById<TextInputLayout>(R.id.login_email_email_layout) }
    val passwordTextLayout by lazy { findViewById<TextInputLayout>(R.id.login_email_pass_layout) }
    val emailEditText by lazy { findViewById<TextInputEditText>(R.id.login_email_email_et) }
    val passwordEditText by lazy { findViewById<TextInputEditText>(R.id.login_email_password_et) }
    val submitButton by lazy { findViewById<Button>(R.id.login_email_submit_btn) }
    val resetPasswordButton by lazy { findViewById<Button>(R.id.login_email_resetPass_btn) }
    val signupButton by lazy { findViewById<Button>(R.id.login_email_signup_btn) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login_email)

        submitButton.setOnClickListener() {
            login()
        }
        resetPasswordButton.setOnClickListener() {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
        signupButton.setOnClickListener() {
            val intent = Intent(this, LoginSignupActivity::class.java)
            startActivity(intent)
        }
        emailTextLayout.editText?.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                emailTextLayout.error = null
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        passwordTextLayout.editText?.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                passwordTextLayout.error = null
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun login() {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Preencha com seu email e senha!", Toast.LENGTH_SHORT).show()
            return
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener

                Log.d("Login", "Successo ao logar!: ${it.result?.user?.uid}")

                // TODO: remove business logic from activity and decouple method
                FirebaseRepository().userLoggedIn(email)

                val intent = Intent(this, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Falha ao logar!: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

}