package com.dhgrupo5.popfilm.network.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.dhgrupo5.popfilm.data.dynamic.UtilDatabase
import com.dhgrupo5.popfilm.model.LastVideo
import com.dhgrupo5.popfilm.network.NetworkRequestMode
import com.dhgrupo5.popfilm.network.UtilNetwork
import com.dhgrupo5.popfilm.notification.UtilNotification


class CatchChannelDataWorker(
    private val context: Context,
    params: WorkerParameters ) : Worker( context, params ) {

    companion object{

        const val NAME = "sync_local_database"
        const val REPEAT_INTERVAL : Long = 18
    }

    override fun doWork(): Result {
        UtilNetwork
            .getInstance( context = context )
            .retrievePlayLists(
                networkRequestMode = NetworkRequestMode.SYNCHRONOUS,
                callbackSuccess = {
                    retrieveLocalLastVideo()
                }
            )

        return Result.success()
    }


    private fun retrieveLocalLastVideo(){
        UtilDatabase
            .getInstance( context = context )
            .getLastVideo{
                retrieveServerLastVideo( oldLastVideo = it )
            }
    }


    private fun retrieveServerLastVideo(
        oldLastVideo: LastVideo? ){

        UtilNetwork
            .getInstance( context = context )
            .retrieveLastVideo(
                networkRequestMode = NetworkRequestMode.SYNCHRONOUS,
                callbackSuccess = {


                    if( oldLastVideo == null
                        || !oldLastVideo.uid.equals( it.uid )
                        || !oldLastVideo.title.equals( it.title )
                        || !oldLastVideo.title.equals( it.description )){

                        UtilNotification
                            .getInstance( context = context )
                            .createBigPictureNotification( lastVideo = it )
                    }
                }
            )
    }
}