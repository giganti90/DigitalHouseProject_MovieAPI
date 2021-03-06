package com.dhgrupo5.popfilm.pack.ui.activity.home

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
import com.dhgrupo5.popfilm.messages.LatestMessagesActivity
import com.dhgrupo5.popfilm.pack.model.GenreDCModelForCategories
import com.dhgrupo5.popfilm.pack.repository.FirebaseRepository
import com.dhgrupo5.popfilm.pack.ui.activity.YoutubeActivity
import com.dhgrupo5.popfilm.pack.ui.activity.login.LoginSocialActivity
import com.dhgrupo5.popfilm.pack.ui.activity.movies.CategoryActivity
import com.dhgrupo5.popfilm.pack.ui.activity.movies.ProfileActivity
import com.dhgrupo5.popfilm.pack.ui.activity.movies.RatingActivity
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    private val toolbar: Toolbar by lazy { findViewById(R.id.hom_tToolbar) }
    private val menuBottomYoutube: LinearLayout by lazy { findViewById(R.id.layout_bot_bar_llBoxFilms) }
    private val menuBottomProfile: LinearLayout by lazy { findViewById(R.id.layout_bot_bar_llBoxPerfil) }
    private val menuBottomMovies: LinearLayout by lazy { findViewById(R.id.layout_bot_bar_llBoxFilms) }
    private val menuBottomRatings: LinearLayout by lazy { findViewById(R.id.layout_bot_bar_llBoxAvaliation) }
    private val menuBottomChat: LinearLayout by lazy { findViewById(R.id.layout_bot_bar_llBoxChat) }
    private val viewmodel: HomeViewModel by viewModels()
    private val recyclerView: RecyclerView by lazy { findViewById(R.id.rv_parent) }
    private lateinit var adapter: HomeGenresAdapter
    private var genres = mutableListOf<GenreDCModelForCategories>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        settingToolbar()
        initRecycler()
        settingClicks()

    }

    private fun initRecycler() {
        adapter = HomeGenresAdapter(genres)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        viewmodel.getGenres()
        viewmodel.genres.observe(this, { genresList ->
            genres.addAll(genresList)
            adapter.notifyDataSetChanged()
        })

//        recyclerView.apply {
//            layoutManager = LinearLayoutManager(
//                this@HomeActivity,
//                LinearLayoutManager.VERTICAL, false
//            )
//            adapter = ParentAdapter(
//                Parent.getParents(40)
//            )
//        }
    }


    //open
    fun openToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun openAbout() {
        val description: String = "Vers??o atual deste aplicativo: " + BuildConfig.VERSION_NAME;

        AlertDialog.Builder(this)
            .setTitle(R.string.app_name)
            .setMessage(description)
            .setPositiveButton(R.string.btn_close) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    fun openLogin() {
        startActivity(
            Intent(this, LoginSocialActivity::class.java)
        )
    }

    fun openLogoff() {
        AlertDialog.Builder(this)
            .setTitle(R.string.exit_title)
            .setMessage(R.string.exit_text)
            .setPositiveButton(R.string.btn_exit) { dialog, _ ->
                openToast(getString(R.string.exit_confirmed));
            }
            .setNegativeButton(R.string.btn_close) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    fun openYoutube() {
        startActivity(
            Intent(this, YoutubeActivity::class.java)
        )
    }

    fun openProfile() {
        startActivity(
            Intent(this, ProfileActivity::class.java)
        )
    }

    fun openMovies() {
        startActivity(
            Intent(this, YoutubeActivity::class.java)
        )
    }

    fun openAvaliations() {
        startActivity(
            Intent(this, RatingActivity::class.java)
        )
    }

    fun openChat() {
        startActivity(
            Intent(this, LatestMessagesActivity::class.java)
        )
        //  Toast.makeText(this, "Em manuten????o!", Toast.LENGTH_SHORT).show()
    }

    fun openCategory() {
        startActivity(
            Intent(this, CategoryActivity::class.java)
        )
        //  Toast.makeText(this, "Em manuten????o!", Toast.LENGTH_SHORT).show()
    }



    //settings
    fun settingToolbar() {
        toolbar.setTitle("")
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.toolbar_textcolor));
        setSupportActionBar(toolbar);
    }

    fun settingClicks() {
        menuBottomYoutube.setOnClickListener {
            openYoutube()
        }
        menuBottomProfile.setOnClickListener {
            openProfile()
        }
        menuBottomMovies.setOnClickListener {
            //openMovies()
            openCategory()
        }
        menuBottomRatings.setOnClickListener {
            openAvaliations()
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
            R.id.menu_hom_mPerfil -> {
                openProfile()
            }
            R.id.menu_hom_mLogoff -> {
                FirebaseAuth.getInstance().signOut()


                val intent = Intent(this, LoginSocialActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)

                // TODO: remove business logic from activity and decouple method
                // This won't work, because there is no identifier available
                FirebaseRepository().userLoggedOff("")
                Toast.makeText(
                    this, "The user will stay on loggedInUsers collection," +
                            " because there is no user identifier available yet", Toast.LENGTH_LONG
                ).show()
            }
        }

        return super.onOptionsItemSelected(item)
    }

}
