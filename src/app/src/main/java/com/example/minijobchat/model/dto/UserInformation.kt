package com.example.minijobchat.model.dto

import com.google.firebase.firestore.DocumentSnapshot

data class UserInformation(
    var email: String = "",
    var uid: String = "",
    var displayName: String = "",
    var photoUrl: String = "",
    var phoneNumber: String = ""
)
