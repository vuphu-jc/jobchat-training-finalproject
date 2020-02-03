package com.example.minijobchat.application

import android.content.Context
import com.example.minijobchat.utils.dagger.AppScope
import dagger.Component

@AppScope
@Component(modules = [ApplicationModule::class])
interface AppComponent {
    fun applicationContext(): Context
}