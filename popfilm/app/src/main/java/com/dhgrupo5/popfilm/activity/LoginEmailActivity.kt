package com.dhgrupo5.popfilm.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.Toast
import com.dhgrupo5.popfilm.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

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
        val todoToast = Toast.makeText(this,getString(R.string.under_construction), Toast.LENGTH_SHORT)
        setContentView(R.layout.activity_login_email)

        submitButton.setOnClickListener() {
            login()
        }
        resetPasswordButton.setOnClickListener() {
            todoToast.show()
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

    fun invalidEmail() = emailEditText.text?.isEmpty() ?: true
    fun invalidPassword() = passwordEditText.text?.isEmpty() ?: true

    fun login() {
        if (!invalidEmail() && !invalidPassword()) {
            val loginToast = Toast.makeText(this,getString(R.string.login_success), Toast.LENGTH_LONG)
            loginToast.show()
            return
        }
        if (invalidEmail()) {
            emailTextLayout.error = getString(R.string.field_required)
        }
        if (invalidPassword()) {
            passwordTextLayout.error = getString(R.string.field_required)
        }
    }
}