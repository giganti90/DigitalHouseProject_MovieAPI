package com.dhgrupo5.popfilm.model

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.config.YouTubeConfig

@Entity
class PlayList(
        @ColumnInfo( name = "title" ) val title: String,
        @PrimaryKey val uid: String,
        val thumb: Int = R.drawable.ic_playlist_color
    ) : ListItem {

    override fun getMainText()
        = title

    override fun getAppUri()
        = Uri.parse(
            String.format(
                YouTubeConfig.Channel.PLAYLIST_URL_TEMPLATE,
                uid
            )
        )

    override fun getIcon()
        = thumb
}