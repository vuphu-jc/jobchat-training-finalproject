package com.example.minijobchat.ui.activity.home.pages.friend

import com.example.minijobchat.model.repository.UserRepository
import com.example.minijobchat.ui.activity.login.LoginPresenter
import com.example.minijobchat.utils.dagger.FragmentScope
import dagger.Module
import dagger.Provides

@Module
class FriendModule {
    @FragmentScope
    @Provides
    fun providePresenter(repository: UserRepository): FriendPresenter {
        return FriendPresenter().apply {
            userRepository = repository
        }
    }
}