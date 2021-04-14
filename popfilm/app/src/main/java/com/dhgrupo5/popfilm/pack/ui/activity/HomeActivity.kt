package com.dhgrupo5.popfilm.pack.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dhgrupo5.popfilm.BuildConfig
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.pack.model.tmdb.movies.Genre
import com.dhgrupo5.popfilm.pack.ui.activity.movies.ProfileActivity
import com.dhgrupo5.popfilm.pack.ui.activity.movies.RatingActivity
import com.dhgrupo5.popfilm.pack.ui.activity.chat.ChatHomeActivity
import com.dhgrupo5.popfilm.pack.ui.activity.login.LoginSocialActivity
import com.dhgrupo5.popfilm.pack.ui.recycleradapter.Parent
import com.dhgrupo5.popfilm.pack.ui.recycleradapter.ParentAdapter
import com.dhgrupo5.popfilm.pack.ui.viewmodel.HomeViewModel
import android.util.Log

class HomeActivity : AppCompatActivity() {

    private val toolbar by lazy { findViewById<Toolbar>(R.id.hom_tToolbar) }
    private val menuBottomYoutube by lazy { findViewById<LinearLayout>(R.id.layout_bot_bar_llBoxFilms) }
    private val menuBottomProfile by lazy { findViewById<LinearLayout>(R.id.layout_bot_bar_llBoxPerfil) }
    private val menuBottomMovies by lazy { findViewById<LinearLayout>(R.id.layout_bot_bar_llBoxFilms) }
    private val menuBottomRatings by lazy { findViewById<LinearLayout>(R.id.layout_bot_bar_llBoxAvaliation) }
    private val menuBottomChat by lazy { findViewById<LinearLayout>(R.id.layout_bot_bar_llBoxChat) }

    private lateinit var recyclerView: RecyclerView
    private val viewmodel: HomeViewModel by viewModels()
    private var genres = MutableLiveData<List<Genre>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        viewmodel.getGenres("pt-BR")
        Log.d("genres",viewmodel.genres.value.toString())
        viewmodel.genres.observe(this) { _genres ->
            genres.postValue(_genres)
        }
        val list = viewmodel.genres.value



        settingToolbar();
        initRecycler()
        settingClicks();

    }

    private fun initRecycler(){
        recyclerView = findViewById(R.id.rv_parent)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity,
                LinearLayoutManager.VERTICAL, false)
            adapter = ParentAdapter(
                Parent
                    .getParents(40))
        }

    }


    //open
    fun openToast(message:String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    fun openAbout(){
        val description :String = "Versão atual deste aplicativo: " + BuildConfig.VERSION_NAME;

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
    fun openProfile(){
        startActivity(
            Intent(this, ProfileActivity::class.java)
        )
    }
    fun openMovies(){
        startActivity(
            Intent(this, YoutubeActivity::class.java)
        )
    }
    fun openRatings(){
        startActivity(
            Intent(this, RatingActivity::class.java)
        )
    }
    fun openChat(){
       startActivity(
            Intent(this, ChatHomeActivity::class.java)
        )
      //  Toast.makeText(this, "Em manutenção!", Toast.LENGTH_SHORT).show()
    }


    //settings
    fun settingToolbar(){
        toolbar.setTitle("")
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.toolbar_textcolor));
        setSupportActionBar(toolbar);
    }
    fun settingClicks(){
        menuBottomYoutube.setOnClickListener {
            openYoutube()
        }
        menuBottomProfile.setOnClickListener {
            openProfile()
        }
        menuBottomMovies.setOnClickListener {
            openMovies()
        }
        menuBottomRatings.setOnClickListener {
            openRatings()
        }
        menuBottomChat.setOnClickListener {
            openChat()
        }
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
        }

        return super.onOptionsItemSelected(item)
    }

}