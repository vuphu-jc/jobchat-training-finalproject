package com.example.minijobchat.ui.activity.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.minijobchat.R
import com.example.minijobchat.application.MainApplication
import com.example.minijobchat.model.dto.User
import com.example.minijobchat.ui.activity.MainActivity
import com.example.minijobchat.ui.activity.home.HomeActivity
import com.example.minijobchat.ui.activity.login.fragment.RegisterEmailFragment
import com.example.minijobchat.ui.activity.login.fragment.RegisterPhoneNumberFragment
import com.example.minijobchat.ui.activity.login.fragment.SubmitLoginFragment
import com.example.minijobchat.ui.activity.login.fragment.SubmitUsernameFragment
import com.example.minijobchat.ui.base.BaseActivity
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginActivityFragment, LoginContract.View {

    @Inject
    lateinit var mPresenter: LoginPresenter
    private var mSubmitUsernameFragment = SubmitUsernameFragment.newInstance(this)

    companion object {
        fun getInstance(context: Context): Intent {
            val intent = Intent(context, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            return intent
        }
    }

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
        mPresenter.submitUsername(userName)
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
        replaceFragment(RegisterEmailFragment.newInstance(this,email))
    }

    override fun allowRegisterByPhoneNumber(phoneNumber: String) {
        replaceFragment(RegisterPhoneNumberFragment.newInstance(this, phoneNumber))
    }

    override fun allowLogin(user: User) {
        replaceFragment(SubmitLoginFragment.newInstance(this, user))
    }

    override fun onLoginSuccess() {
        startActivity(HomeActivity.getInstance(this))
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
