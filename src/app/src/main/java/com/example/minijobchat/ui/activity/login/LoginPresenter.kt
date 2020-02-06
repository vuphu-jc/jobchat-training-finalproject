package com.example.minijobchat.ui.activity.login

import com.example.minijobchat.model.dto.User
import com.example.minijobchat.model.repository.UserRepository
import com.example.minijobchat.utils.StringUtils
import com.google.android.gms.tasks.TaskExecutors
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class LoginPresenter: LoginContract.Presenter {
    lateinit var mUserRepository: UserRepository
    private var mView: LoginContract.View? = null
    private var mFirebaseAuth = FirebaseAuth.getInstance()
    private val mCompositeDisposable = CompositeDisposable()

    override fun detach() {
        mView = null
        mCompositeDisposable.clear()
    }

    override fun attach(view: LoginContract.View) {
        mView = view
    }

    override fun submitUsername(userName: String) {
        mView?.showProgress()
        var email = userName
        if (StringUtils.isPhoneNumber(userName))
            email = getEmail(userName)
        val dispose = mUserRepository.findUserByUsername(email)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({user ->
                if (user == null) {
                    allowRegister(userName)
                } else {
                    mView?.allowLogin(user)
                    mView?.hideProgress()
                }
            }, {
                mView?.hideProgress()
            })
        mCompositeDisposable.add(dispose)
    }

    private fun allowRegister(userName: String) {
        if (StringUtils.isEmail(userName)) {
            mView?.allowRegisterByEmail(userName)
            mView?.hideProgress()
        }
        else if (StringUtils.isPhoneNumber(userName)) {
            val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                }

                override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                    super.onCodeSent(p0, p1)
                    mView?.allowRegisterByPhoneNumber(userName)
                    mView?.hideProgress()
                }
            }

            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                userName, 1, TimeUnit.SECONDS, TaskExecutors.MAIN_THREAD, callbacks)

        }
    }

    override fun register(userName: String, password: String, displayName: String) {
        if (StringUtils.isEmail(userName)) {
            val email = userName
            register(User(email =  email, displayName =  displayName), password)
        }
        else if (StringUtils.isPhoneNumber(userName)) {
            val phoneNumber = userName
            register(User(email = getEmail(phoneNumber),
                displayName =  displayName,
                phoneNumber =  phoneNumber), password)
        }
    }

    private fun register(userInformation: User, password: String) {
        mView?.showProgress()
        val dispose= mUserRepository.register(
            email = userInformation.email,
            displayName =  userInformation.displayName,
            phoneNumber =  userInformation.phoneNumber,
            password = password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                mView?.hideProgress()
                login(userInformation.email, password)
            }, {
                mView?.hideProgress()
            })
        mCompositeDisposable.add(dispose)
    }

    override fun login(email: String, password: String) {
        mView?.showProgress()
        mFirebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) mView?.onLoginSuccess()
                else {
                    var message = it.exception?.message
                    if (message == null) message = ""
                    mView?.onLoginFailed(message)
                }
                mView?.hideProgress()
            }
            .addOnFailureListener {
                mView?.hideProgress()
            }
    }

    private fun getEmail(phoneNumber: String): String {
        return StringUtils.filterNumber(phoneNumber) + "@jobchat.vn"
    }
}