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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dhgrupo5.popfilm.BuildConfig
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.pack.model.tmdb.movies.Genre
import com.dhgrupo5.popfilm.pack.ui.activity.movies.ProfileActivity
import com.dhgrupo5.popfilm.pack.ui.activity.movies.RatingActivity
import com.dhgrupo5.popfilm.pack.ui.activity.chat.ChatHomeActivity
import com.dhgrupo5.popfilm.pack.ui.activity.login.LoginSocialActivity
import com.dhgrupo5.popfilm.pack.ui.recycleradapter.ParentAdapter
import com.dhgrupo5.popfilm.pack.ui.viewmodel.HomeViewModel
import android.util.Log
import com.dhgrupo5.popfilm.pack.ui.recycleradapter.Parent
import com.dhgrupo5.popfilm.pack.ui.recycleradapter.ParentModel
import com.dhgrupo5.popfilm.pack.utils.moviesdb.GenresList

class HomeActivity : AppCompatActivity() {

    private val toolbar by lazy { findViewById<Toolbar>(R.id.hom_tToolbar) }
    private val menuBottomYoutube by lazy { findViewById<LinearLayout>(R.id.layout_bot_bar_llBoxFilms) }
    private val menuBottomProfile by lazy { findViewById<LinearLayout>(R.id.layout_bot_bar_llBoxPerfil) }
    private val menuBottomMovies by lazy { findViewById<LinearLayout>(R.id.layout_bot_bar_llBoxFilms) }
    private val menuBottomRatings by lazy { findViewById<LinearLayout>(R.id.layout_bot_bar_llBoxAvaliation) }
    private val menuBottomChat by lazy { findViewById<LinearLayout>(R.id.layout_bot_bar_llBoxChat) }

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.rv_parent) }
    private val viewmodel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        startAPIService()
        settingToolbar()
        initRecycler()
        settingClicks()

    }

    private fun startAPIService() {
        viewmodel.genresLiveData.observe(this) { genresList ->
            GenresList.genres = genresList
            recyclerView.adapter?.notifyDataSetChanged()
        }
        viewmodel.getGenres("pt-BR")
    }

    private fun initRecycler(){
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity,
                LinearLayoutManager.VERTICAL, false)
            adapter = getAdapterFromList(GenresList.genres)
//            adapter = ParentAdapter(
//                Parent
//                    .getParents(40))
        }

    }

    private fun getAdapterFromList(genres: List<Genre>): ParentAdapter {
        val parents = mutableListOf<ParentModel>()
        genres.forEach { genre -> parents.add(ParentModel(genre.name, listOf())) }
        return ParentAdapter(parents)
    }


    //open
    private fun openToast(message:String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    private fun openAbout(){
        val description :String = "Versão atual deste aplicativo: " + BuildConfig.VERSION_NAME;

        AlertDialog
            .Builder(this)
            .setTitle(R.string.app_name)
            .setMessage(description)
            .setPositiveButton(R.string.btn_close) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }
    private fun openLogin(){
        startActivity(
            Intent(this, LoginSocialActivity::class.java)
        )
    }
    private fun openLogoff(){
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
    private fun openYoutube(){
        startActivity(
            Intent(this, YoutubeActivity::class.java)
        )
    }
    private fun openProfile(){
        startActivity(
            Intent(this, ProfileActivity::class.java)
        )
    }
    private fun openMovies(){
        startActivity(
            Intent(this, YoutubeActivity::class.java)
        )
    }
    private fun openRatings(){
        startActivity(
            Intent(this, RatingActivity::class.java)
        )
    }
    private fun openChat(){
       startActivity(
            Intent(this, ChatHomeActivity::class.java)
        )
      //  Toast.makeText(this, "Em manutenção!", Toast.LENGTH_SHORT).show()
    }


    //settings
    private fun settingToolbar(){
        toolbar.setTitle("")
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.toolbar_textcolor));
        setSupportActionBar(toolbar);
    }
    private fun settingClicks(){
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