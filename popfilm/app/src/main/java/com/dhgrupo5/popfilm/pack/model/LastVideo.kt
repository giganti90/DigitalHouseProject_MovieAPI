package com.dhgrupo5.popfilm.model

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dhgrupo5.popfilm.config.YouTubeConfig


@Entity
data class LastVideo(
        @PrimaryKey val uid: String,
        @ColumnInfo( name = "title" ) val title: String,
        @ColumnInfo( name = "description" ) val description: String = ""
    ) : Parcelable {


    @ColumnInfo( name = "thumb_url" )
    var thumbUrl: String = ""
        set( value ) {
            field = if( value.isNotEmpty() ){
                value
            }
            else{
                alternativeThumbUrl()
            }
        }


    private fun alternativeThumbUrl()
        = String.format(
            YouTubeConfig.Channel.VIDEO_THUMB_URL_TEMPLATE,
            uid
        )

    fun webUri()
        = Uri.parse(
            String.format(
                YouTubeConfig.Channel.VIDEO_URL_TEMPLATE,
                uid
            )
        )


    constructor( source: Parcel ) : this (
        source.readString()!!,
        source.readString()!!,
        source.readString()!!
    )

    override fun describeContents() = 0

    override fun writeToParcel(
        dest: Parcel,
        flags: Int )
        = with( dest ){
            writeString( uid )
            writeString( title )
            writeString( description )
        }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<LastVideo>
            = object : Parcelable.Creator<LastVideo> {

            override fun createFromParcel( source: Parcel ) : LastVideo
                = LastVideo( source )

            override fun newArray( size: Int ) : Array<LastVideo?>
                = arrayOfNulls( size )
        }
    }
}