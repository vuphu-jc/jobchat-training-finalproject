package com.example.minijobchat.model.api

import android.content.Context
import com.example.minijobchat.R
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor(){

    companion object {
        private val sInstance: RetrofitClient by lazy { RetrofitClient() }
        private lateinit var sContext: Context

        fun getInstance(): RetrofitClient {
            return sInstance
        }

        fun initializeContext(context: Context) {
            sContext = context
        }
    }

    private var mRetrofit: Retrofit

    init {
        val apiURL = "http://${sContext.getString(R.string.ip_server)}:5000/"
        mRetrofit = Retrofit.Builder().baseUrl(apiURL)
            .client(
                OkHttpClient.Builder()
                .addInterceptor(NetworkConnectionInterceptor(sContext))
                .build())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun getAPIService(): RetrofitAPIService {
        return mRetrofit.create(RetrofitAPIService::class.java)
    }
}