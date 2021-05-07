package com.dhgrupo5.popfilm.data.dynamic

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dhgrupo5.popfilm.data.fixed.LastVideoData
import com.dhgrupo5.popfilm.data.fixed.PlayListsData

class InitialDataCallback(
    private val context: Context ) : RoomDatabase.Callback() {

    override fun onCreate( db: SupportSQLiteDatabase ) {
        super.onCreate( db )
        initLastVideo()
        initPlayLists()
    }

    private fun initLastVideo(){
        UtilDatabase
            .getInstance( context = context )
            .saveLastVideo(
                lastVideo = LastVideoData.getInitialVideo()
            )
    }

    private fun initPlayLists(){
        UtilDatabase
            .getInstance( context = context )
            .savePlayLists(
                playLists = PlayListsData.getInitialPlayLists()
            )
    }
}