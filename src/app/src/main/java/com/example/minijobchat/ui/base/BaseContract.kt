package com.example.minijobchat.ui.base

interface BaseContract {
    interface View {
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter<in T> {
        fun detach()
        fun attach(view: T)
    }
}