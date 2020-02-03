package com.example.minijobchat.application

import android.content.Context
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
}