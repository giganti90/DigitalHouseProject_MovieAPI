package com.dhgrupo5.popfilm.pack.ui.activity.movies

import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.MediaController
import android.widget.ProgressBar
import android.widget.VideoView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.pack.model.MovieResponse
import com.dhgrupo5.popfilm.pack.ui.activity.home.HomeViewModel
import com.dhgrupo5.popfilm.pack.utils.moviesdb.NetworkUtils
import com.google.android.exoplayer2.*

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

    //comuns
    var videoUrl: String = "http://techslides.com/demos/sample-videos/small.mp4";



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_play)

        settingToolbar()
        //settingPlayer()
        //settingVideoView()
        getExtras()

    }


    //settings
    fun settingToolbar(){
        toolbar.setTitle(getString(R.string.title_activity_movie_play))
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.toolbar_textcolor))
        setSupportActionBar(toolbar)
        var actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }
    fun settingPlayer(){
//        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this)
//        playerView.player = simpleExoPlayer;
    }
    fun settingVideoView(){
        val videoView = findViewById<VideoView>(R.id.videoView)
        //Creating MediaController
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        //specify the location of media file
        //val uri:Uri = Uri.parse(Environment.getExternalStorageDirectory().getPath() + "/Movies/video.mp4")
        val uri:Uri = Uri.parse(videoUrl)
        //Setting MediaController and URI, then starting the videoView
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        videoView.start()

        progress.visibility = ProgressBar.GONE
    }
    fun getExtras(){
        val informacoes = intent.extras

        if (informacoes != null) {
            movieResponse = informacoes.getSerializable("movie") as MovieResponse
            if(movieResponse != null){
                Log.i("MoviePlay", "Essa ?? a identifica????o do movie: ${movieResponse.id}")
            }
        }
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