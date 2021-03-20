package com.dhgrupo5.popfilm.data.dynamic

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dhgrupo5.popfilm.model.LastVideo
import com.dhgrupo5.popfilm.model.PlayList

@Database(
    entities = arrayOf(
        LastVideo::class,
        PlayList::class
    ),
    version = 20
)
abstract class ChannelDatabase: RoomDatabase() {

    companion object{

        private const val DB_NAME = "youtube-channel"

        private var instance: ChannelDatabase? = null

        fun getInstance( context: Context) : ChannelDatabase {

            if( instance == null || !instance!!.isOpen ){

                instance = Room.databaseBuilder(
                    context,
                    ChannelDatabase::class.java,
                    DB_NAME
                )
                .addCallback(
                    InitialDataCallback(
                        context = context
                    )
                )
                .fallbackToDestructiveMigration()
                .build()
            }
            return instance!!
        }
    }

    abstract fun lastVideoDao() : LastVideoDao
    abstract fun playListDao() : PlayListDao
}









