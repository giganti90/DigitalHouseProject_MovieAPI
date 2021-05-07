package com.dhgrupo5.popfilm.data.fixed

import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.model.SocialNetwork


abstract class SocialNetworksData {

    companion object{

        fun getNetworks()
            = listOf(

                SocialNetwork(
                    network = "YouTube",
                    accountName = "Marvel Oficial",
                    appUri = "",
                    webUri = "https://www.youtube.com/user/MARVEL",
                    logo = R.drawable.ic_youtube_color
                )
            )
    }
}