package com.dhgrupo5.popfilm.pack.repository

import com.google.common.base.Strings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseRepository {
    val firestore = Firebase.firestore

    fun userLoggedIn(identifier: String) {
        firestore.collection("loggedInUsers")
            .document(identifier)
            .set(hashMapOf("identifier" to identifier))
    }

    fun userLoggedOff(identifier: String?) {
        if (identifier.isNullOrEmpty()) return
        firestore.collection("loggedInUsers")
            .document(identifier)
            .delete()
    }

    fun newRating(identifier: String) {
        firestore.collection("ratings")
            .document(identifier)
            .set(hashMapOf("identifier" to identifier))
    }
}