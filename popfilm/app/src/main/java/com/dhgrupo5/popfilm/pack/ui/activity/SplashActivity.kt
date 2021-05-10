package com.dhgrupo5.popfilm.pack

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.pack.ui.activity.HomeActivity
import com.dhgrupo5.popfilm.pack.ui.activity.login.LoginEmailActivity
import com.dhgrupo5.popfilm.pack.ui.activity.login.LoginSocialActivity
import com.google.firebase.auth.FirebaseAuth



class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )


        val backgroundImage: ImageView = findViewById(R.id.image_splash_screen)
        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.side_slide)
        backgroundImage.startAnimation(slideAnimation)

        Handler(Looper.getMainLooper()).postDelayed({
            jump()
        },3000)
    }
    private fun jump() {
        if (FirebaseAuth.getInstance().currentUser == null)
            startActivity(Intent(this, LoginSocialActivity::class.java))
        else
            startActivity(Intent(this, HomeActivity::class.java))
        finish()

    }
}
