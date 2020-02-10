package com.example.minijobchat.ui.base

interface BaseContract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun onError(message: String?)
        fun onError(resId: Int)
    }

    interface Presenter<in T> {
        fun detach()
        fun attach(view: T)
    }
}