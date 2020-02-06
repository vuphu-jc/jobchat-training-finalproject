package com.example.minijobchat.ui.activity.login

import com.example.minijobchat.application.AppComponent
import com.example.minijobchat.model.repository.UserInformationRepository
import com.example.minijobchat.model.repository.UserInformationRepositoryImp
import com.example.minijobchat.utils.dagger.ActivityScope
import com.example.minijobchat.utils.dagger.FragmentScope
import dagger.Component
import dagger.Module
import dagger.Provides

@Module
class LoginModule {
    @ActivityScope
    @Provides
    fun provideUserInformationRepository(): UserInformationRepository {
        return UserInformationRepositoryImp()
    }

    @ActivityScope
    @Provides
    fun providePresenter(repository: UserInformationRepository): LoginPresenter {
        return LoginPresenter().apply {
            mUserInformationRepository = repository
        }
    }
}