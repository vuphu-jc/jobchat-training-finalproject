package com.example.minijobchat.application

import android.content.Context
import com.example.minijobchat.model.repository.UserRepository
import com.example.minijobchat.model.repository.UserRepositoryImp
import com.example.minijobchat.utils.dagger.ActivityScope
import com.example.minijobchat.utils.dagger.AppScope
import dagger.Module
import dagger.Provides

@AppScope
@Module
class ApplicationModule(context: Context) {

    private val mContext: Context = context

    @Provides
    public fun provideApplicationContext(): Context {
        return mContext
    }

    @Provides
    fun provideUserRepository(): UserRepository {
        return UserRepositoryImp()
    }
}
