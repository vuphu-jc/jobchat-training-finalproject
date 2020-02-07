package com.example.minijobchat.ui.activity.login

import com.example.minijobchat.model.dto.User
import com.example.minijobchat.ui.base.BaseContract

interface LoginActivityCallback {
    fun onSubmitUserName(userName: String)
    fun onSubmitLogin(userName: String, password: String)
    fun onRegister(userName: String, password: String, displayName: String)
    fun onBackFragment()
}

interface LoginContract {
    interface View: BaseContract.View {
        fun allowRegisterByEmail(email: String)
        fun allowRegisterByPhoneNumber(phoneNumber: String)
        fun allowLogin(user: User)
        fun onLoginSuccess()
        fun onLoginFailed(message: String)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun submitUsername(userName: String)
        fun register(userName: String, password: String, displayName: String)
        fun login(email: String, password: String)
    }
}
