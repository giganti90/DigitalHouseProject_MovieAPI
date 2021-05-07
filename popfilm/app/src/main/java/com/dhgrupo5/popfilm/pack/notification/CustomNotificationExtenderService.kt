package com.dhgrupo5.popfilm.notification

import android.net.UrlQuerySanitizer
import com.dhgrupo5.popfilm.config.OneSignalConfig
import com.dhgrupo5.popfilm.config.YouTubeConfig
import com.dhgrupo5.popfilm.data.dynamic.UtilDatabase
import com.dhgrupo5.popfilm.model.LastVideo
import com.onesignal.NotificationExtenderService
import com.onesignal.OSNotificationReceivedResult
import org.json.JSONObject
import java.net.URI


class CustomNotificationExtenderService: NotificationExtenderService() {


    override fun onNotificationProcessing(
        notification: OSNotificationReceivedResult? ): Boolean {

        val lastVideo = getLastVideoFromJson(
            json = notification?.payload?.additionalData
        )

        if( lastVideo != null ){
            ifNewLastVideoThenSaveAndNotify( lastVideo = lastVideo )
        }

        return true
    }

    private fun getLastVideoFromJson(
        json: JSONObject? ) : LastVideo? {


        if( json == null
            || json.isNull( OneSignalConfig.Notification.VIDEO )
            || json.isNull( OneSignalConfig.Notification.TITLE ) ){
            return null
        }

        val url = json.getString( OneSignalConfig.Notification.VIDEO )
        val title = json.getString( OneSignalConfig.Notification.TITLE )
        var lastVideo : LastVideo? = null

        if( !url.isEmpty() && !title.isEmpty() ){

            val urlQuery = UrlQuerySanitizer( url )

            if( !urlQuery.getValue( YouTubeConfig.Notification.VIDEO_PARAM ).isNullOrEmpty() ){
                lastVideo = LastVideo(
                    uid = urlQuery.getValue( YouTubeConfig.Notification.VIDEO_PARAM ),
                    title = title,
                    description = getDescriptionFromJson( json )
                )
                .apply {
                    thumbUrl = YouTubeConfig.Notification.EMPTY
                }
            }
            else if( url.contains( YouTubeConfig.Notification.ALTERNATIVE_URL ) ){
                val uri = URI( url )
                val path: String = uri.getPath()
                val uid = path
                    .substring(
                        path.lastIndexOf('/') + 1
                    )

                lastVideo = LastVideo(
                    uid = uid,
                    title = title,
                    description = getDescriptionFromJson( json )
                )
                .apply {
                    thumbUrl = YouTubeConfig.Notification.EMPTY
                }
            }
        }

        return lastVideo
    }


    private fun getDescriptionFromJson( json: JSONObject )
        = if( !json.isNull( OneSignalConfig.Notification.DESCRIPTION ) ){
            json.getString( OneSignalConfig.Notification.DESCRIPTION )
        }
        else {
            OneSignalConfig.Notification.EMPTY
        }

    private fun ifNewLastVideoThenSaveAndNotify(
        lastVideo : LastVideo ){

        UtilDatabase
            .getInstance( context = this )
            .getLastVideo{

                if( it == null
                    || !it.uid.equals( lastVideo.uid )
                    || !it.title.equals( lastVideo.title )
                    || !it.description.equals( lastVideo.description ) ){

                    UtilDatabase
                        .getInstance( context = this )
                        .saveLastVideo( lastVideo = lastVideo )

                    UtilNotification
                        .getInstance( context = this )
                        .createBigPictureNotification( lastVideo = lastVideo )
                }
            }
    }
}