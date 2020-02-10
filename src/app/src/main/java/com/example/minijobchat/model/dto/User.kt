package com.example.minijobchat.model.dto

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email") var email: String = "",
    @SerializedName("uid") var uid: String = "",
    @SerializedName("display_name") var displayName: String = "",
    @SerializedName("photo_url") var photoUrl: String = "",
    @SerializedName("phone_number") var phoneNumber: String = "",
    @SerializedName("dob") var dateOfBirth: String = "",
    @SerializedName("introduce") var introduce: String = ""
)
