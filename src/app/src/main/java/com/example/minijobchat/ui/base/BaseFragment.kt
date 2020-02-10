package com.example.minijobchat.ui.base

import android.app.ProgressDialog
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.minijobchat.R

abstract class BaseFragment: Fragment(), BaseContract.View {
    private lateinit var mProgressDialog: ProgressDialog

    override fun showProgress() {
        mProgressDialog = ProgressDialog(activity, R.style.ProgressBarTheme)
        mProgressDialog.setCancelable(false)
        mProgressDialog.show()
    }

    override fun hideProgress() {
        mProgressDialog.dismiss()
    }

    override fun onError(message: String?) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun onError(resId: Int) {
        onError(getString(resId))
    }
}