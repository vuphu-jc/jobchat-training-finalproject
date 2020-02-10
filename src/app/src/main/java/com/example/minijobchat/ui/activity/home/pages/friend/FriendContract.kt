package com.example.minijobchat.ui.activity.home.pages.friend

import com.example.minijobchat.model.dto.User
import com.example.minijobchat.ui.base.BaseContract

interface FriendContract {
    interface View: BaseContract.View {
        fun onFindUserSuccess(user: User)
        fun onNotFoundUser()
        fun onMakeFriendSuccess()
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun findUserByUsername(userName: String)
        fun makeFriend(uid: String)
    }
}
