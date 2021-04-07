package com.dhgrupo5.popfilm.pack.ui.activity

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.config.YouTubeConfig
import com.dhgrupo5.popfilm.data.fixed.MenuItemsData
import com.dhgrupo5.popfilm.fragment.AboutChannelFragment
import com.dhgrupo5.popfilm.model.MenuItem
import com.dhgrupo5.popfilm.ui.YoutubeActivityForegroundStatus
import com.dhgrupo5.popfilm.ui.adapter.MenuAdapter
import com.dhgrupo5.popfilm.ui.fragment.*
import kotlinx.android.synthetic.main.activity_youtube.*


class YoutubeActivity : AppCompatActivity() {

    companion object{

        const val LOG_DEBUG = "log_channel_app"

        const val FRAG_STACK_ID = "frag_stack_id"

        var APP_FOREGROUND = YoutubeActivityForegroundStatus.IS_NOT_IN_FOREGROUND
    }

    override fun onCreate( savedInstanceState: Bundle? ){
        setTheme( R.style.AppTheme )
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_youtube )

        if( supportFragmentManager.findFragmentByTag( FRAG_STACK_ID ) == null ){
            changeFragment(
                fragment = LastVideoFragment(),
                fragKey = getFragmentInKey()
            )
        }

        initBottomMenu()
    }

    override fun onResume() {
        super.onResume()
        removeStatusBarNotification()
        APP_FOREGROUND = YoutubeActivityForegroundStatus.IS_IN_FOREGROUND
    }

    override fun onPause() {
        super.onPause()
        APP_FOREGROUND = YoutubeActivityForegroundStatus.IS_NOT_IN_FOREGROUND
    }

    override fun onBackPressed(){

        supportFragmentManager
            .popBackStack(
                null,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )

        super.onBackPressed()
    }

    private fun removeStatusBarNotification(){
        val notificationManager = getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager

        notificationManager.cancelAll()
    }

    private fun initBottomMenu(){

        val layoutManager = GridLayoutManager(
            this,
            MenuAdapter.NUMBER_COLUMNS,
            RecyclerView.HORIZONTAL,
            false
        )
        rv_menu.layoutManager = layoutManager

        rv_menu.setHasFixedSize( true )
        rv_menu.adapter = MenuAdapter(
            context = this,
            items = MenuItemsData.getItems( res = resources ),
            changeFragmentCallback = {
                    item -> fragmentOnScreen( item = item )
            }
        )
    }

    private fun fragmentOnScreen( item: MenuItem){
        val fragment = getFragment( itemId = item.id )
        val fragKey = getFragmentInKey( itemId = item.id )

        changeFragment(
            fragment = fragment,
            fragKey = fragKey
        )
    }

    private fun getFragment(
        itemId: Int = R.id.last_video ) : Fragment {

        val key = getFragmentInKey( itemId = itemId )

        var fragment = supportFragmentManager
            .findFragmentByTag( key )

        if( fragment == null ){
            fragment = when( itemId ){
                R.id.social_networks -> SocialNetworksFragment()
                R.id.play_lists -> PlayListsFragment()
                R.id.about_channel -> AboutChannelFragment()
                R.id.business -> BusinessContactsFragment()
                else -> LastVideoFragment()
            }
        }

        return fragment
    }

    private fun getFragmentInKey(
        itemId: Int = R.id.last_video )
            = when( itemId ){
        R.id.social_networks -> SocialNetworksFragment.KEY
        R.id.play_lists -> PlayListsFragment.KEY
        R.id.about_channel -> AboutChannelFragment.KEY
        R.id.business -> BusinessContactsFragment.KEY
        else -> LastVideoFragment.KEY
    }

    private fun changeFragment(
        fragment: Fragment,
        fragKey: String ){

        val fragTransaction = supportFragmentManager.beginTransaction()
        fragTransaction.setCustomAnimations(
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )

        fragTransaction.replace(
            R.id.ll_content_container,
            fragment,
            fragKey
        )
        fragTransaction.addToBackStack( FRAG_STACK_ID )

        fragTransaction.commit()
    }

    fun openYouTubeChannel( view: View){
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse( YouTubeConfig.Channel.CHANNEL_URL )
        )

        if( intent.resolveActivity( packageManager ) != null ){
            startActivity( intent )
        }
        else{
            Toast
                .makeText(
                    this,
                    getString( R.string.channel_toast_alert ),
                    Toast.LENGTH_LONG
                )
                .show()
        }
    }
}