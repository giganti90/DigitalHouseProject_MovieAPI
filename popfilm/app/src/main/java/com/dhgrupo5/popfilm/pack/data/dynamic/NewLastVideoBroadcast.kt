package com.dhgrupo5.popfilm.data.dynamic

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.dhgrupo5.popfilm.model.LastVideo
import com.dhgrupo5.popfilm.ui.fragment.LastVideoFragment

class NewLastVideoBroadcast(
        private val fragment: LastVideoFragment
    ): BroadcastReceiver() {

    companion object{

        const val FILTER_KEY = "LocalBroadcastLastVideo_key"

        const val DATA_KEY = "LastVideo_key"
    }

    override fun onReceive(
        context: Context,
        data: Intent ){

        val lastVideo = data
            .getParcelableExtra<LastVideo>( DATA_KEY )!!

        fragment.newLastVideoData( video = lastVideo )
    }
}