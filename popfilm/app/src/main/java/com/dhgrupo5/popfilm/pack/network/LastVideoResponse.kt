package com.dhgrupo5.popfilm.network

import android.content.Context
import com.dhgrupo5.popfilm.data.dynamic.UtilDatabase
import com.dhgrupo5.popfilm.model.LastVideo
import com.dhgrupo5.popfilm.model.parse.video.VideoParse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LastVideoResponse(
    private val context: Context,
    private val callbackSuccess: (LastVideo)->Unit = {},
    private val callbackFailure: (NetworkRetrieveDataProblem)->Unit = {}
    ) : Callback<VideoParse> {

    override fun onResponse(
        call: Call<VideoParse>,
        response: Response<VideoParse> ){
        parse( response = response )
    }

    override fun onFailure(
        call: Call<VideoParse>,
        t: Throwable ){
        callbackFailure( NetworkRetrieveDataProblem.NO_INTERNET_CONNECTION )
    }


    fun parse( response: Response<VideoParse> ){

        if( response.isSuccessful ){
            val video = response.body()!!

            if( video.id.isNotEmpty() ){
                val lastVideo = LastVideo(
                    uid = video.id,
                    title = video.title,
                    description = video.description
                ).apply {
                    thumbUrl = video.thumbUrl
                }

                UtilDatabase
                    .getInstance( context = context )
                    .saveLastVideo( lastVideo = lastVideo )

                callbackSuccess( lastVideo )
            }
            else{
                callbackFailure( NetworkRetrieveDataProblem.NO_VIDEO )
            }
        }
        else{
            callbackFailure( NetworkRetrieveDataProblem.NO_INTERNET_CONNECTION )
        }
    }
}