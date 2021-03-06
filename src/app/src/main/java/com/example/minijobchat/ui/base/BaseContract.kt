package com.example.minijobchat.ui.base

interface BaseContract {
    interface View {
    }

    interface Presenter<in T> {
        fun detach()
        fun attach(view: T)
    }
}