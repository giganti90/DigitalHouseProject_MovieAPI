package com.dhgrupo5.popfilm.model

import android.net.Uri


class BusinessContact(
    val place: String,
    val contact: String,
    private val webUri: String,
    private val logo: Int ) : ListItem {

    override fun getMainText()
        = String.format(
            "%s: %s",
            place,
            contact
        )

    override fun getWebUri()
        = Uri.parse( webUri )

    override fun getIcon()
        = logo
}