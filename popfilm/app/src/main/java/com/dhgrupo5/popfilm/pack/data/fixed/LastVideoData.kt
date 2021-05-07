package com.dhgrupo5.popfilm.data.fixed

import com.dhgrupo5.popfilm.model.LastVideo

class LastVideoData {

    companion object{

        fun getInitialVideo()
            = LastVideo(
                uid = "IWBsDaFWyTE",
                title = "Trailer The Falcon and The Winter Soldier ",

                description = " Trailer oficial da nova série da Disney Plus ",

            ).apply {
                thumbUrl = ""
            }
    }
}