package com.example.minijobchat.ui.base

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.minijobchat.R


abstract class BaseActivity: AppCompatActivity(), BaseContract.View {
    private lateinit var mProgressDialog: ProgressDialog

    override fun showProgress() {
        mProgressDialog = ProgressDialog(this, R.style.ProgressBarTheme)
        mProgressDialog.setCancelable(false)
        mProgressDialog.show()
    }

    override fun hideProgress() {
        mProgressDialog.dismiss()
    }
}