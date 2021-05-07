package com.dhgrupo5.popfilm.data.fixed

import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.model.BusinessContact

abstract class BusinessContactData {

    companion object{

        fun getBusinessContacts()
            = listOf(
                BusinessContact(
                    place = "Gmail",
                    contact = "dhousegrupo5@gmail.com",
                    webUri = "Grupo 5 Digital House",
                    logo = R.drawable.ic_gmail_color
                ),

            )
    }
}