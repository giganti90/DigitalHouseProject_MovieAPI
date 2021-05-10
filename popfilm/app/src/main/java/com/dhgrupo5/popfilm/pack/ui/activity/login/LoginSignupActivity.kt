package com.dhgrupo5.popfilm.pack.ui.activity.login

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.messages.LatestMessagesActivity
import com.dhgrupo5.popfilm.models.User
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class LoginSignupActivity : AppCompatActivity() {

    val nameEditText by lazy { findViewById<TextInputEditText>(R.id.login_signup_name_et) }
    val emailEditText by lazy { findViewById<TextInputEditText>(R.id.login_signup_email_et) }
    val passwordEditText by lazy { findViewById<TextInputEditText>(R.id.login_signup_pasword_et) }
    val tosCheckbox by lazy { findViewById<CheckBox>(R.id.login_signup_tos_checkbox) }
    val submitButton by lazy { findViewById<Button>(R.id.login_signup_submit_btn) }
    val already_have_account_text_view by lazy { findViewById<TextView>(R.id.already_have_account_text_view) }
    val selectphoto_imageview_register by lazy { findViewById<ImageView>(R.id.selectphoto_imageview_register) }

    companion object {
        val TAG = "RegisterActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_signup)


        submitButton.setOnClickListener {
            performRegister()
        }

        already_have_account_text_view.setOnClickListener {
            Log.d(LoginSignupActivity.TAG, "Try to show login activity")

            // launch the login activity somehow
            val intent = Intent(this, LoginEmailActivity::class.java)
            startActivity(intent)
        }

        selectphoto_imageview_register.setOnClickListener {
            Log.d(LoginSignupActivity.TAG, "Try to show photo selector")

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }
    }

    var selectedPhotoUri: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            // proceed and check what the selected image was....
            Log.d(LoginSignupActivity.TAG, "Photo was selected")

            selectedPhotoUri = data.data

            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)

            selectphoto_imageview_register.setImageBitmap(bitmap)

            selectphoto_imageview_register.alpha = 0f

        }
    }

    private fun performRegister() {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter text in email/pw", Toast.LENGTH_SHORT).show()
            return
        }

        Log.d(LoginSignupActivity.TAG, "Attempting to create user with email: $email")

        // Firebase Authentication to create a user with email and password
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener

                // else if successful
                Log.d(LoginSignupActivity.TAG, "Successfully created user with uid: ${it.result!!.user!!.uid}")

                uploadImageToFirebaseStorage()
            }
            .addOnFailureListener{
                Log.d(LoginSignupActivity.TAG, "Failed to create user: ${it.message}")
                Toast.makeText(this, "Failed to create user: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun uploadImageToFirebaseStorage() {
        if (selectedPhotoUri == null) return

        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

        ref.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                Log.d(LoginSignupActivity.TAG, "Successfully uploaded image: ${it.metadata?.path}")

                ref.downloadUrl.addOnSuccessListener {
                    Log.d(LoginSignupActivity.TAG, "File Location: $it")

                    saveUserToFirebaseDatabase(it.toString())
                }
            }
            .addOnFailureListener {
                Log.d(LoginSignupActivity.TAG, "Failed to upload image to storage: ${it.message}")
            }
    }

    private fun saveUserToFirebaseDatabase(profileImageUrl: String) {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

        val user = User(uid, nameEditText.text.toString(), profileImageUrl)

        ref.setValue(user)
            .addOnSuccessListener {
                Log.d(LoginSignupActivity.TAG, "Finally we saved the user to Firebase Database")

                val intent = Intent(this, LatestMessagesActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)

            }
            .addOnFailureListener {
                Log.d(LoginSignupActivity.TAG, "Failed to set value to database: ${it.message}")
            }
    }

}