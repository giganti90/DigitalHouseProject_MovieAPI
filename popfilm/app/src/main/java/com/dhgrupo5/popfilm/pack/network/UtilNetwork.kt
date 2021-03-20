package com.dhgrupo5.popfilm.network

import android.content.Context
import com.dhgrupo5.popfilm.config.YouTubeConfig
import com.dhgrupo5.popfilm.model.LastVideo
import com.dhgrupo5.popfilm.model.PlayList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class UtilNetwork private constructor(
    private val context: Context ){

    companion object{

        private var instance: UtilNetwork? = null

        fun getInstance( context: Context ) : UtilNetwork {
            if( instance == null ){
                instance = UtilNetwork( context = context )
            }
            return instance!!
        }
    }

    private fun getYouTubeService()
        = Retrofit.Builder()
            .baseUrl( YouTubeConfig.ApiV3.BASE_URL )
            .addConverterFactory( GsonConverterFactory.create() )
            .build()
            .create( YouTubeService::class.java )


    fun retrieveLastVideo(
        networkRequestMode: NetworkRequestMode = NetworkRequestMode.ASYNCHRONOUS,
        callbackSuccess: (LastVideo)->Unit = {},
        callbackFailure: (NetworkRetrieveDataProblem)->Unit = {} ){

        val service = getYouTubeService()
        val call = service.lastVideo()
        val lastVideoResponse = LastVideoResponse(
            context = context,
            callbackSuccess = callbackSuccess,
            callbackFailure = callbackFailure
        )

        if( networkRequestMode == NetworkRequestMode.ASYNCHRONOUS ){
            call.enqueue( lastVideoResponse )
        }
        else{
            try{
                lastVideoResponse.parse(
                    response = call.execute()
                )
            }
            catch( e: Exception ){}
        }
    }


    fun retrievePlayLists(
        networkRequestMode: NetworkRequestMode = NetworkRequestMode.ASYNCHRONOUS,
        callbackSuccess: (List<PlayList>)->Unit = {},
        callbackFailure: (NetworkRetrieveDataProblem)->Unit = {} ){

        val service = getYouTubeService()
        val call = service.playLists()
        val playListsResponse = PlayListsResponse(
            context = context,
            callbackSuccess = callbackSuccess,
            callbackFailure = callbackFailure
        )

        if( networkRequestMode == NetworkRequestMode.ASYNCHRONOUS ){
            call.enqueue( playListsResponse )
        }
        else{
            try{
                playListsResponse.parse(
                    response = call.execute()
                )
            }
            catch( e: Exception ){}
        }
    }
}