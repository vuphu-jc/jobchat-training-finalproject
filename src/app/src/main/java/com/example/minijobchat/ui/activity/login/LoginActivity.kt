package com.example.minijobchat.ui.activity.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.minijobchat.R
import com.example.minijobchat.application.MainApplication
import com.example.minijobchat.model.dto.UserInformation
import com.example.minijobchat.ui.activity.MainActivity
import com.example.minijobchat.ui.activity.login.fragment.RegisterEmailFragment
import com.example.minijobchat.ui.activity.login.fragment.RegisterPhoneNumberFragment
import com.example.minijobchat.ui.activity.login.fragment.SubmitLoginFragment
import com.example.minijobchat.ui.activity.login.fragment.SubmitUsernameFragment
import com.example.minijobchat.ui.base.BaseActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginActivityFragment, LoginContract.View {

    private var mSubmitUsernameFragment = SubmitUsernameFragment().apply {
        activityFragment = this@LoginActivity
    }

    @Inject
    lateinit var mPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        injectDependency()
        initialize()
    }

    override fun onStart() {
        super.onStart()
        mPresenter.attach(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detach()
    }

    override fun onSubmitUserName(userName: String) {
        mPresenter.submitUserName(userName)
    }

    override fun onSubmitLogin(userName: String, password: String) {
        mPresenter.login(userName, password)
    }

    override fun onRegister(userName: String, password: String, displayName: String) {
        mPresenter.register(userName, password, displayName)
    }

    override fun onBackFragment() {
        onBackPressed()
    }

    override fun allowRegisterByEmail(email: String) {
        replaceFragment(RegisterEmailFragment().apply {
            activityFragment = this@LoginActivity
            this.email = email
        })
    }

    override fun allowRegisterByPhoneNumber(phoneNumber: String) {
        replaceFragment(RegisterPhoneNumberFragment().apply {
            activityFragment = this@LoginActivity
            this.phoneNumber = phoneNumber
        })
    }

    override fun allowLogin(userInformation: UserInformation) {
        replaceFragment(SubmitLoginFragment().apply {
            activityFragment = this@LoginActivity
            this.userInformation = userInformation
        })
    }

    override fun onLoginSuccess() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun onLoginFailed(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun injectDependency() {
        DaggerLoginComponent.builder()
            .appComponent((application as MainApplication).getAppComponent())
            .loginModule(LoginModule())
            .build()
            .inject(this)
    }

    private fun initialize() {
        replaceFragment(mSubmitUsernameFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        if (fragment != mSubmitUsernameFragment)
            transaction.setCustomAnimations(R.anim.push_enter, R.anim.push_exit, R.anim.pop_enter, R.anim.pop_exit)
        transaction.replace(R.id.mainFragment, fragment)
        if (supportFragmentManager.fragments.size > 0 &&
            supportFragmentManager.fragments[0] != null && supportFragmentManager.fragments[0] == mSubmitUsernameFragment)
            transaction.addToBackStack(mSubmitUsernameFragment::class.java.name)
        transaction.commit()
    }
}
