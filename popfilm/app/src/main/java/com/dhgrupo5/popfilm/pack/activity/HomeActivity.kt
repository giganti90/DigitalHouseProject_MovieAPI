package com.dhgrupo5.popfilm.pack.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.dhgrupo5.popfilm.BuildConfig
import com.dhgrupo5.popfilm.R

class HomeActivity : AppCompatActivity() {

    val toolbar by lazy { findViewById<Toolbar>(R.id.hom_tToolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        settingToolbar();

    }


    //open
    fun openToast(message:String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    fun openAbout(){
        var description :String = "Versão atual deste aplicativo: " + BuildConfig.VERSION_NAME;

        AlertDialog
            .Builder(this)
            .setTitle(R.string.app_name)
            .setMessage(description)
            .setPositiveButton(R.string.btn_close) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }
    fun openLogin(){
        startActivity(
            Intent(this, LoginSocialActivity::class.java)
        )
    }
    fun openLogoff(){
        AlertDialog
            .Builder(this)
            .setTitle(R.string.exit_title)
            .setMessage(R.string.exit_text)
            .setPositiveButton(R.string.btn_exit) { dialog, _ ->
                openToast(getString(R.string.exit_confirmed));
            }
            .setNegativeButton(R.string.btn_close){ dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
    fun openYoutube(){
        startActivity(
            Intent(this, YoutubeActivity::class.java)
        )
    }


    //settings
    fun settingToolbar(){
        toolbar.setTitle(R.string.menu_home)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.toolbar_textcolor));
        setSupportActionBar(toolbar);
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_home, menu)
        menuInflater.inflate(R.menu.menu_login, menu)
        menuInflater.inflate(R.menu.menu_logoff, menu)

        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_hom_mAbout -> {
                openAbout()
            }
            R.id.menu_hom_mLogin -> {
                openLogin()
            }
            R.id.menu_hom_mLogoff -> {
                openLogoff()
            }
            R.id.menu_hom_mYoutube -> {
                openYoutube()
            }
        }

        return super.onOptionsItemSelected(item)
    }


}