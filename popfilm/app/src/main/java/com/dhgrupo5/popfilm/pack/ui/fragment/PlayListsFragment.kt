package com.dhgrupo5.popfilm.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.data.dynamic.UtilDatabase
import com.dhgrupo5.popfilm.data.fixed.PlayListsData
import com.dhgrupo5.popfilm.model.PlayList
import com.dhgrupo5.popfilm.network.UtilNetwork
import com.dhgrupo5.popfilm.ui.adapter.ListItemAdapter
import kotlinx.android.synthetic.main.fragment_last_video.*
import kotlinx.android.synthetic.main.fragment_last_video.srl_update_content
import kotlinx.android.synthetic.main.fragment_play_lists.*
import kotlinx.android.synthetic.main.no_data_message_block.*


class PlayListsFragment : Fragment() {

    companion object {

        const val KEY = "PlayListsFragment_key"
    }


    private val playLists = mutableListOf<PlayList>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {

        return inflater.inflate(
            R.layout.fragment_play_lists,
            container,
            false
        )
    }

    override fun onActivityCreated( savedInstanceState: Bundle? ){
        super.onActivityCreated( savedInstanceState )

        setListener()
        initPlayListList()

        if( playLists.isNotEmpty() ){
            setUiModel( pLists = playLists )
        }
        else{

            playLists.addAll( PlayListsData.getInitialPlayLists() )

            uiDataStatus( status = UiFragLoadDataStatus.LOADING )
            UtilDatabase
                .getInstance( context = activity!!.applicationContext )
                .getAllPlayLists{
                    val auxPlayList = if( it.isNullOrEmpty() ) {
                        playLists
                    }
                    else {
                        it
                    }
                    setUiModel( pLists = auxPlayList )
                }
        }
    }

    private fun setListener(){
        srl_update_content.setOnRefreshListener {
            swipeRefreshStatus( status = true )
            retrieveData()
        }
    }


    private fun setUiModel( pLists: List<PlayList>? ){

        if( !pLists.isNullOrEmpty() ){
            activity?.runOnUiThread {
                uiDataStatus(
                    status = UiFragLoadDataStatus.LOADED
                )

                if (!pLists.equals( playLists )) {
                    playLists.clear()
                    playLists.addAll( pLists )
                }

                rv_play_lists
                    ?.adapter
                    ?.notifyDataSetChanged()
            }
        }
        else{
            uiDataStatus(
                status = UiFragLoadDataStatus.NO_MAIN_CONTENT
            )
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
            .retrievePlayLists(
                callbackSuccess = {
                    swipeRefreshStatus( status = false )
                    setUiModel( it )
                },
                callbackFailure = {
                    swipeRefreshStatus( status = false )
                }
            )
    }

    private fun uiDataStatus( status: UiFragLoadDataStatus ){

        activity?.runOnUiThread {
            var rvPlayLists = View.GONE
            var rlNoDataMessageContainer = View.VISIBLE
            var tvNoDataStatus = View.GONE
            swipeRefreshStatus( status = false )
            pb_load_content?.hide()

            when( status ){
                UiFragLoadDataStatus.LOADING -> {
                    pb_load_content?.show()
                }
                UiFragLoadDataStatus.NO_MAIN_CONTENT -> {
                    tv_no_data?.text = getString( R.string.no_playlists_yet )
                    tvNoDataStatus = View.VISIBLE
                }
                else -> {
                    rlNoDataMessageContainer = View.GONE
                    rvPlayLists = View.VISIBLE
                }
            }

            rv_play_lists?.visibility = rvPlayLists
            rl_no_data_message_container?.visibility = rlNoDataMessageContainer
            tv_no_data?.visibility = tvNoDataStatus
        }
    }

    private fun initPlayListList(){
        val layoutManager = LinearLayoutManager( activity )
        rv_play_lists?.layoutManager = layoutManager

        rv_play_lists?.setHasFixedSize( true )
        rv_play_lists?.adapter = ListItemAdapter(
            context = activity!!,
            items = playLists,
            callExternalAppCallback = {
                item -> callYouTubePlayListCallback(
                    playList = item as PlayList
                )
            }
        )
    }

    private fun callYouTubePlayListCallback(
        playList: PlayList ){

        val intent = Intent(
            Intent.ACTION_VIEW,
            playList.getAppUri()
        )

        if( intent.resolveActivity( activity!!.packageManager ) == null ){
            Toast
                .makeText(
                    activity,
                    String.format(
                        getString( R.string.playlist_toast_alert ),
                        playList.title
                    ),
                    Toast.LENGTH_LONG
                )
                .show()

            return
        }

        activity!!.startActivity( intent )
    }
}