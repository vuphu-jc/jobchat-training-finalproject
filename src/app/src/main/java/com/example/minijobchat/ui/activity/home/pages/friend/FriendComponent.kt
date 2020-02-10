package com.example.minijobchat.ui.activity.home.pages.friend

import com.example.minijobchat.application.AppComponent
import com.example.minijobchat.ui.activity.login.LoginActivity
import com.example.minijobchat.ui.activity.login.LoginModule
import com.example.minijobchat.utils.dagger.FragmentScope
import dagger.Component

@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [FriendModule::class])
interface FriendComponent {
    fun inject(activity: FriendFragment?)
}
