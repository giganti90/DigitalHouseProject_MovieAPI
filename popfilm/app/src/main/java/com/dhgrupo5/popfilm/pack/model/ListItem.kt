package com.dhgrupo5.popfilm.model

import android.content.res.Resources
import android.net.Uri

interface ListItem {


    fun getMainText() : String

    fun getFirstAuxText() : String
        = ""

    fun getSecondAuxText( resource: Resources ) : String
        = ""

    fun getWebUri() : Uri?
        = null


    fun getAppUri() : Uri?
        = null


    fun getIcon() : Int
}