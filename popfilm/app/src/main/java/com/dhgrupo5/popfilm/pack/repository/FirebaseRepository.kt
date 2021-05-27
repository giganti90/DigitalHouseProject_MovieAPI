package com.dhgrupo5.popfilm.pack.repository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseRepository {
    val firestore = Firebase.firestore

    fun userLoogedIn(identifier: String) {
        firestore.collection("loggedInUsers")
            .add(identifier)
    }

    fun newRating(identifier: String) {
        firestore.collection("ratings")
            .add(identifier)
    }
}