package com.dhgrupo5.popfilm.model

import android.net.Uri

class SocialNetwork(
    val network: String,
    private val accountName: String,
    private val appUri: String,
    private val webUri: String,
    private val logo: Int ) : ListItem {

    override fun getMainText()
        = String.format(
            "%s: %s",
            network,
            accountName
        )

    override fun getWebUri()
        = Uri.parse( webUri )

    override fun getAppUri()
        = Uri.parse( appUri )

    override fun getIcon()
        = logo
}