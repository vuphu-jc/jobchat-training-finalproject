package com.example.minijobchat.model.api

import com.example.minijobchat.model.dto.UserInformation
import retrofit2.http.*
import rx.Observable

interface RetrofitAPIService {
    @GET("/user-information")
    fun getUserInformation(@Query("username") username: String): Observable<UserInformation?>

    @FormUrlEncoded
    @POST("register")
    fun registerUser(@Field("email") email: String,
                     @Field("displayName") displayName: String,
                     @Field("phoneNumber") phoneNumber: String,
                     @Field("password") password: String): Observable<Boolean>
}
