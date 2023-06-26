package com.example.myapplicationb.viewmodel

import com.example.myapplicationb.data.User
import com.example.myapplicationb.model.repository.FirebaseRepository
import com.example.myapplicationb.model.repository.FirebaseRepositoryImpl

class MainActivityViewModel {

    private val mFirebaseRepository: FirebaseRepository = FirebaseRepositoryImpl()

    fun updateUserData(firebaseUser: User, uid: String) {
        mFirebaseRepository.updateUserData(firebaseUser, uid)
    }
}