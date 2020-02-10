package com.example.minijobchat.model.repository

import com.example.minijobchat.R
import com.example.minijobchat.model.api.RetrofitClient
import com.example.minijobchat.model.dto.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Completable
import io.reactivex.Observable

interface UserRepository {
    fun findUserByUsername(username: String): Observable<User>
    fun register(user: User, password: String): Completable
}

class UserRepositoryImp: UserRepository {
    val mAuth = FirebaseAuth.getInstance()

    override fun findUserByUsername(username: String): Observable<User> {
        return RetrofitClient.getInstance().getAPIService()
            .getUserInformation(username)
    }

    override fun register(user: User, password: String): Completable {
        val hashMap = HashMap<String, Any>()
        hashMap["user"] = user
        hashMap["password"] = password
        return RetrofitClient.getInstance().getAPIService()
            .registerUser(hashMap)
    }
}
