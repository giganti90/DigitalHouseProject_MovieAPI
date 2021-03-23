package com.dhgrupo5.popfilm.network

import android.content.Context
import com.dhgrupo5.popfilm.data.dynamic.UtilDatabase
import com.dhgrupo5.popfilm.model.PlayList
import com.dhgrupo5.popfilm.model.parse.playlist.PlayListsParse
import retrofit2.Callback

class PlayListsResponse(
    private val context: Context,
    private val callbackSuccess: (List<PlayList>)->Unit = {},
    private val callbackFailure: (NetworkRetrieveDataProblem)->Unit = {}
) : Callback<PlayListsParse> {

    override fun onResponse(
        call: retrofit2.Call<PlayListsParse>,
        response: retrofit2.Response<PlayListsParse>
    ){
        parse( response = response )
    }

    override fun onFailure(
        call: retrofit2.Call<PlayListsParse>,
        t: Throwable ){

        callbackFailure( NetworkRetrieveDataProblem.NO_INTERNET_CONNECTION )
    }

    fun parse( response: retrofit2.Response<PlayListsParse>){

        if( response.isSuccessful ){
            val playListParse = response.body()!!

            if( playListParse.items.isNotEmpty() ){
                val playLists = mutableListOf<PlayList>()

                playListParse.items.map{
                    playLists.add(
                        PlayList(
                            uid = it.id,
                            title = it.title
                        )
                    )
                }

                UtilDatabase
                    .getInstance( context = context )
                    .savePlayLists( playLists = playLists )

                callbackSuccess( playLists )
            }
            else{
                callbackFailure( NetworkRetrieveDataProblem.NO_PLAYLISTS )
            }
        }
        else{
            callbackFailure( NetworkRetrieveDataProblem.NO_INTERNET_CONNECTION )
        }
    }
}