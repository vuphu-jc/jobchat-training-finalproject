package com.example.minijobchat.ui.activity.login

import com.example.minijobchat.model.repository.UserRepository
import com.example.minijobchat.model.repository.UserRepositoryImp
import com.example.minijobchat.utils.dagger.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class LoginModule {
    @ActivityScope
    @Provides
    fun providePresenter(repository: UserRepository): LoginPresenter {
        return LoginPresenter().apply {
            mUserRepository = repository
        }
    }
}
