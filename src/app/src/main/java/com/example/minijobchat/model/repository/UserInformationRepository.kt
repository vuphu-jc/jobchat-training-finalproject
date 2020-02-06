package com.example.minijobchat.model.repository

import com.example.minijobchat.model.api.RetrofitClient
import com.example.minijobchat.model.dto.UserInformation
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import rx.Observable

interface UserInformationRepository {
    fun findByUsername(username: String): Observable<UserInformation?>
    fun register(email: String, displayName: String, phoneNumber: String, password: String): Observable<Boolean>
}

class UserInformationRepositoryImp: UserInformationRepository {
    private val mDatabase = FirebaseFirestore.getInstance()

    override fun findByUsername(username: String): Observable<UserInformation?> {
        return RetrofitClient.getInstance().getAPIService()
            .getUserInformation(username)
    }

    override fun register(email: String, displayName: String, phoneNumber: String, password: String): Observable<Boolean> {
        return RetrofitClient.getInstance().getAPIService()
            .registerUser(email, displayName, phoneNumber, password)
    }
}