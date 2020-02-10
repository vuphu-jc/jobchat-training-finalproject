package com.example.minijobchat.application

import android.content.Context
import com.example.minijobchat.model.dto.FriendRequest
import com.example.minijobchat.model.realtime.RealtimeData
import com.example.minijobchat.model.realtime.RealtimeRepository
import com.example.minijobchat.model.repository.UserRepository
import com.example.minijobchat.utils.dagger.AppScope
import dagger.Component

@AppScope
@Component(modules = [ApplicationModule::class])
interface AppComponent {
    fun applicationContext(): Context
    fun getUserRepository(): UserRepository
    fun getRealtimeRepository(): RealtimeRepository
    fun getRealtimeFriendRequest(): RealtimeData<FriendRequest>
}
