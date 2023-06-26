package com.example.myapplicationb.model.repository

import com.example.myapplicationb.data.User

interface FirebaseRepository {

    fun updateUserData(firebaseUser: User, uid: String)
}