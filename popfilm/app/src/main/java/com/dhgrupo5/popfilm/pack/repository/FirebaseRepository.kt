package com.dhgrupo5.popfilm.pack.repository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseRepository {
    val firestore = Firebase.firestore

    fun userLoggedIn(identifier: String) {
        firestore.collection("loggedInUsers")
            .add(identifier)
    }

    fun userLoggedOff(identifier: String) {
        firestore.collection("loggedInUsers")
            .document(identifier)
    }

    fun newRating(identifier: String) {
        firestore.collection("ratings")
            .add(identifier)
    }
}