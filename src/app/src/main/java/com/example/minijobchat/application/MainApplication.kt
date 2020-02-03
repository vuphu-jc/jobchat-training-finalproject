package com.example.minijobchat.application

import android.app.Application

class MainApplication: Application() {

    private var mAppComponent: AppComponent? = null

    fun getAppComponent(): AppComponent {
        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder()
                .applicationModule(ApplicationModule(applicationContext))
                .build()
        }
        return mAppComponent as AppComponent
    }
}