package com.example.minijobchat.model.api

import com.example.minijobchat.model.dto.User
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.*

interface RetrofitAPIService {
    @Headers("Content-Type: application/json")
    @POST("user")
    fun registerUser(@Body jsonContent: HashMap<String,Any>): Completable

    @GET("user/{username}")
    fun getUserInformation(@Path("username") username: String): Observable<User>
}

