package com.dhgrupo5.popfilm.network

import com.dhgrupo5.popfilm.config.YouTubeConfig
import com.dhgrupo5.popfilm.model.parse.playlist.PlayListsParse
import com.dhgrupo5.popfilm.model.parse.video.VideoParse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeService {

    @GET( value = YouTubeConfig.ApiV3.VIDEO_PATH )
    fun lastVideo(
        @Query( value = "key" )
        key: String = YouTubeConfig.Key.GOOGLE_DEV,
        @Query( value = "channelId" )
        channelId: String = YouTubeConfig.Channel.CHANNEL_ID,
        @Query( value = "part" )
        part: String = YouTubeConfig.ApiV3.PART_PARAM,
        @Query( value = "maxResults" )
        maxResults: String = YouTubeConfig.ApiV3.MAX_RESULTS_VIDEO_PARAM,
        @Query( value = "order" )
        order: String = YouTubeConfig.ApiV3.ORDER_PARAM
    ): Call<VideoParse>


    @GET( value = YouTubeConfig.ApiV3.PLAYLISTS_PATH )
    fun playLists(
        @Query( value = "key" )
        key: String = YouTubeConfig.Key.GOOGLE_DEV,
        @Query( value = "channelId" )
        channelId: String = YouTubeConfig.Channel.CHANNEL_ID,
        @Query( value = "part" )
        part: String = YouTubeConfig.ApiV3.PART_PARAM,
        @Query( value = "maxResults" )
        maxResults: String = YouTubeConfig.ApiV3.MAX_RESULTS_PLAYLISTS_PARAM,
        @Query( value = "order" )
        order: String = YouTubeConfig.ApiV3.ORDER_PARAM
    ): Call<PlayListsParse>
}