package com.dhgrupo5.popfilm.pack.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.dhgrupo5.popfilm.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginLostPasswordActivity : AppCompatActivity() {
    val closeButton by lazy { findViewById<ImageView>(R.id.login_lost_password_close_button) }
    val emailTextLayout by lazy { findViewById<TextInputLayout>(R.id.login_lost_password_email_layout) }
    val emailEditText by lazy { findViewById<TextInputEditText>(R.id.login_lost_password_email_et) }
    val submitButton by lazy { findViewById<Button>(R.id.login_lost_password_submit_button) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_lost_password)
        closeButton.setOnClickListener() {
            finish()
        }
        emailEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                emailTextLayout.error = null
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        submitButton.setOnClickListener() {
            submit()
        }
    }

    fun invalidEmail() = emailEditText.text?.isEmpty() ?: true

    private fun submit() {
        if (!invalidEmail()) {
            Toast.makeText(this,getString(R.string.lost_password_success), Toast.LENGTH_LONG)
                    .show()
            finish()
        }
        else {
            emailTextLayout.error = getString(R.string.field_required)
        }
    }

}