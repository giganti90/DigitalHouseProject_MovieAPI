package com.dhgrupo5.popfilm.ui.fragment

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.data.dynamic.NewLastVideoBroadcast
import com.dhgrupo5.popfilm.data.dynamic.UtilDatabase
import com.dhgrupo5.popfilm.data.fixed.LastVideoData
import com.dhgrupo5.popfilm.model.LastVideo
import com.dhgrupo5.popfilm.network.UtilNetwork
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_last_video.*

class LastVideoFragment : Fragment() {

    companion object {

        const val KEY = "LastVideoFragment_key"
    }


    private var lastVideo: LastVideo = LastVideoData.getInitialVideo()

    private lateinit var localBroadcast: NewLastVideoBroadcast


    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        initLocalBroadcast()


        UtilDatabase
            .getInstance( context = activity!!.applicationContext )
            .getLastVideo{
                setUiModel( lVideo = it )
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {

        return inflater.inflate(
            R.layout.fragment_last_video,
            container,
            false
        )
    }

    override fun onActivityCreated( savedInstanceState: Bundle? ) {
        super.onActivityCreated( savedInstanceState )
        setListeners()
        setUiModel( lVideo = lastVideo )
    }

    override fun onDestroy(){
        super.onDestroy()
        destroyLocalBroadcast()
    }

    private fun setListeners(){
        srl_update_content?.setOnRefreshListener {
            swipeRefreshStatus( status = true )
            retrieveData()
        }
        ll_last_video_container?.setOnClickListener{
            openVideoOnYouTube()
        }
    }

    private fun setUiModel( lVideo: LastVideo? ){

        if( lVideo != null ){
            activity?.runOnUiThread{
                lastVideo = lVideo

                try{
                    Picasso
                        .get()
                        .load( lVideo.thumbUrl )
                        .into( iv_last_video_thumb )

                    iv_last_video_thumb?.contentDescription = lVideo.title
                }
                catch( e: Exception ){}

                tv_last_video_title?.text = lVideo.title
                descriptionStatus( description = lVideo.description )
            }
        }
    }


    private fun descriptionStatus( description: String ){

        if( description.isNotEmpty() ){
            tv_last_video_desc?.text = description
            tv_last_video_desc?.visibility = View.VISIBLE
        }
        else{
            tv_last_video_desc?.visibility = View.GONE
        }
    }


    private fun swipeRefreshStatus( status : Boolean ){
        activity?.runOnUiThread {
            srl_update_content?.isRefreshing = status
        }
    }

    private fun retrieveData(){
        UtilNetwork
            .getInstance( context = activity!! )
            .retrieveLastVideo(
                callbackSuccess = {
                    swipeRefreshStatus( status = false )
                    setUiModel( lVideo = it )
                },
                callbackFailure = {
                    swipeRefreshStatus( status = false )
                }
            )
    }


    private fun openVideoOnYouTube(){
        val intent = Intent(
            Intent.ACTION_VIEW,
            lastVideo.webUri()
        )

        if( intent.resolveActivity( activity!!.packageManager ) == null ){
            Toast
                .makeText(
                    activity,
                    String.format(
                        getString( R.string.last_video_toast_alert ),
                        lastVideo.title
                    ),
                    Toast.LENGTH_LONG
                )
                .show()

            return
        }

        activity!!.startActivity( intent )
    }


    private fun initLocalBroadcast(){
        val intentFilter = IntentFilter( NewLastVideoBroadcast.FILTER_KEY )

        localBroadcast = NewLastVideoBroadcast(
            fragment = this
        )

        LocalBroadcastManager
            .getInstance( activity!! )
            .registerReceiver(
                localBroadcast,
                intentFilter
            )
    }


    private fun destroyLocalBroadcast(){
        LocalBroadcastManager
            .getInstance( activity!! )
            .unregisterReceiver( localBroadcast )
    }


    fun newLastVideoData( video: LastVideo ){
        lastVideo = video
        setUiModel( lVideo =  video )
    }
}