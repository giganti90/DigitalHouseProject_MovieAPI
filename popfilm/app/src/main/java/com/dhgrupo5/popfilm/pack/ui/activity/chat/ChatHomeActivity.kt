package com.dhgrupo5.popfilm.pack.ui.activity.chat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.messages.LatestMessagesActivity
import com.dhgrupo5.popfilm.registerlogin.LoginActivityChat

class ChatHomeActivity : AppCompatActivity() {

    val btn_chathome by lazy <Button> { findViewById(R.id.btn_chathome) }
    val btn_logar_firebase by lazy <Button> { findViewById(R.id.btn_logar_firebase) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_home)


        btn_chathome.setOnClickListener() {
            val intent = Intent(this, LatestMessagesActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_logar_firebase.setOnClickListener() {
            val intent = Intent(this, LoginActivityChat::class.java)
            startActivity(intent)
            finish()
        }
    }

}