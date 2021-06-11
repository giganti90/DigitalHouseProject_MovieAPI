package com.dhgrupo5.popfilm.pack.ui.activity.movies

import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.MediaController
import android.widget.ProgressBar
import android.widget.VideoView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.pack.model.DiscoverResponse
import com.dhgrupo5.popfilm.pack.model.MovieResponse
import com.dhgrupo5.popfilm.pack.repository.MoviesAPIRepository
import com.dhgrupo5.popfilm.pack.ui.activity.home.HomeViewModel
import com.dhgrupo5.popfilm.pack.ui.adapter.CategoryInfoAdapterForCategories
import com.dhgrupo5.popfilm.pack.utils.moviesdb.NetworkUtils
import com.google.android.exoplayer2.*
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MoviePlayActivity : AppCompatActivity() {

    //objects
    val toolbar by lazy { findViewById<Toolbar>(R.id.layout_too_tPadrao) }
    val progress by lazy { findViewById<ProgressBar>(R.id.progress) }
    //val playerView by lazy { findViewById<PlayerView>(R.id.mov_pla_pvPlayer) }
    //var simpleExoPlayer: SimpleExoPlayer = TODO();
    //private lateinit var videoView: PlayerView
    private lateinit var exoPlayer: ExoPlayer
    private lateinit var context: Context
    private lateinit var movieResponse: MovieResponse
    //private val viewModel by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java) }
    val repository by lazy { MoviesAPIRepository() }
    private val viewModel by lazy { ViewModelProviders.of(this).get(MovieDetailsViewModel::class.java)}

    //comuns
    var videoUrl: String = "http://techslides.com/demos/sample-videos/small.mp4";



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_play)

        settingToolbar()
        //settingPlayer()
        //settingVideoView()

        val informacoes = intent.extras
        movieResponse = informacoes?.getSerializable("movie") as MovieResponse
        Log.i("MoviePlay", "Essa é a identificação do movie: ${movieResponse.id}")

        viewModel.configMovieID(Integer.parseInt(movieResponse.id))

        showButtonTrailer()

    }


    //settings
    fun settingToolbar(){
        toolbar.setTitle(getString(R.string.title_activity_movie_play))
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.toolbar_textcolor))
        setSupportActionBar(toolbar)
        var actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }
    private fun showButtonTrailer() {
        viewModel.trailerLiveData.observe(this) { idTrailer ->
            if(idTrailer.isNotEmpty()){
                Log.i("MoviePlayActivity", "Chegou o idTrailer: ${idTrailer}")
                playMovie(idTrailer)
            }
        }
    }
    fun playMovie(movieId: String){
        val youTubePlayerView = findViewById<YouTubePlayerView>(R.id.youtube_player_view)
        lifecycle.addObserver(youTubePlayerView)

        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                if (movieId != null) {
                    youTubePlayer.loadVideo(movieId, 0f)
                }
            }
        })
    }


    //overrides
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item)
    }
}