package com.example.minijobchat.model.repository

import com.example.minijobchat.model.api.RetrofitClient
import com.example.minijobchat.model.dto.User
import io.reactivex.Completable
import io.reactivex.Observable

interface UserRepository {
    fun findUserByUsername(username: String): Observable<User?>
    fun register(email: String, displayName: String, phoneNumber: String, password: String): Completable
}

class UserRepositoryImp: UserRepository {
    override fun findUserByUsername(username: String): Observable<User?> {
        return RetrofitClient.getInstance().getAPIService()
            .getUserInformation(username)
    }

    override fun register(email: String, displayName: String, phoneNumber: String, password: String): Completable {
        return RetrofitClient.getInstance().getAPIService()
            .registerUser(email, displayName, phoneNumber, password)
    }
}
