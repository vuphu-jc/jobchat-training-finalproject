package com.example.minijobchat.model.api

import com.example.minijobchat.model.dto.User
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.*

interface RetrofitAPIService {
    @GET("/user-information")
    fun getUserInformation(@Query("username") username: String): Observable<User?>

    @FormUrlEncoded
    @POST("register")
    fun registerUser(@Field("email") email: String,
                     @Field("displayName") displayName: String,
                     @Field("phoneNumber") phoneNumber: String,
                     @Field("password") password: String): Completable
}

