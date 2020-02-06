package com.example.minijobchat.application

import android.app.Application
import com.example.minijobchat.model.api.RetrofitClient
import com.google.firebase.FirebaseApp

class MainApplication: Application() {

    private var mAppComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        RetrofitClient.initializeContext(this)
    }

    fun getAppComponent(): AppComponent {
        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder()
                .applicationModule(ApplicationModule(applicationContext))
                .build()
        }
        return mAppComponent as AppComponent
    }
}