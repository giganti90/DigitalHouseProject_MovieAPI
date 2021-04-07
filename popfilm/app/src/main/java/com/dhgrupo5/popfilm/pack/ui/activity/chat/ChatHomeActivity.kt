package com.dhgrupo5.popfilm.pack.ui.activity.chat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.dhgrupo5.popfilm.R

class ChatHomeActivity : AppCompatActivity() {

    val btn_chathome by lazy <Button> { findViewById(R.id.btn_chathome) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_home)


        btn_chathome.setOnClickListener() {
            val intent = Intent(this, ChatActivity::class.java)
            startActivity(intent)
        }

    }

}