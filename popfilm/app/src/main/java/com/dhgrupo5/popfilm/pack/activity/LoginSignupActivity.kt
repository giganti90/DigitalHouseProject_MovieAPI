package com.dhgrupo5.popfilm.pack.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.Toast
import com.dhgrupo5.popfilm.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginSignupActivity : AppCompatActivity() {
    val closeButton by lazy { findViewById<ImageView>(R.id.login_signup_close_button) }
    val nameTextLayout by lazy { findViewById<TextInputLayout>(R.id.login_signup_name_layout) }
    val phoneTextLayout by lazy { findViewById<TextInputLayout>(R.id.login_signup_phone_layout) }
    val emailTextLayout by lazy { findViewById<TextInputLayout>(R.id.login_signup_email_layout) }
    val passwordTextLayout by lazy { findViewById<TextInputLayout>(R.id.login_signup_paswword_layout) }
    val nameEditText by lazy { findViewById<TextInputEditText>(R.id.login_signup_name_et) }
    val phoneEditText by lazy { findViewById<TextInputEditText>(R.id.login_signup_phone_et) }
    val emailEditText by lazy { findViewById<TextInputEditText>(R.id.login_signup_email_et) }
    val passwordEditText by lazy { findViewById<TextInputEditText>(R.id.login_signup_pasword_et) }
    val tosCheckbox by lazy { findViewById<CheckBox>(R.id.login_signup_tos_checkbox) }
    val submitButton by lazy { findViewById<Button>(R.id.login_signup_submit_btn) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_signup)

        closeButton.setOnClickListener() {
            finish()
        }
        nameEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                nameTextLayout.error = null
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        phoneEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                phoneTextLayout.error = null
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        emailEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                emailTextLayout.error = null
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        passwordEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                passwordTextLayout.error = null
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        submitButton.setOnClickListener() {
            submit()
        }
    }

    fun invalidName() = nameEditText.text?.isEmpty() ?: true
    fun invalidPhone() = phoneEditText.text?.isEmpty() ?: true
    fun invalidEmail() = emailEditText.text?.isEmpty() ?: true
    fun invalidPassword() = passwordEditText.text?.isEmpty() ?: true
    fun tosNotAccepted() = !tosCheckbox.isChecked

    fun submit() {
        if (!invalidName() && !invalidPhone() &&!invalidEmail() && !invalidPassword() && !tosNotAccepted()) {
            Toast.makeText(this,getString(R.string.signup_success), Toast.LENGTH_LONG)
                    .show()
            finish()
            return
        }
        if (invalidName()) {
            nameTextLayout.error = getString(R.string.field_required)
        }
        if (invalidPhone()) {
            phoneTextLayout.error = getString(R.string.field_required)
        }
        if (invalidEmail()) {
            emailTextLayout.error = getString(R.string.field_required)
        }
        if (invalidPassword()) {
            passwordTextLayout.error = getString(R.string.field_required)
        }
        if (tosNotAccepted()) {
            Toast.makeText(this,getString(R.string.tos_not_accepted), Toast.LENGTH_LONG)
                .show()
        }
    }

}