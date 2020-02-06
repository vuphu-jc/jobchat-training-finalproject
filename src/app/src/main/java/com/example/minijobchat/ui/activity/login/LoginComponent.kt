package com.example.minijobchat.ui.activity.login

import androidx.fragment.app.Fragment
import com.example.minijobchat.application.AppComponent
import com.example.minijobchat.utils.dagger.ActivityScope
import com.example.minijobchat.utils.dagger.FragmentScope
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [LoginModule::class])
interface LoginComponent {
    fun inject(activity: LoginActivity?)
}
