package com.example.minijobchat.ui.activity.login

import android.util.Log
import com.example.minijobchat.model.dto.UserInformation
import com.example.minijobchat.model.repository.UserInformationRepository
import com.example.minijobchat.utils.StringUtils
import com.google.android.gms.tasks.TaskExecutors
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.UserProfileChangeRequest
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class LoginPresenter: LoginContract.Presenter {
    private var mView: LoginContract.View? = null
    private var mFirebaseAuth = FirebaseAuth.getInstance()
    lateinit var mUserInformationRepository: UserInformationRepository

    override fun detach() {
        mView = null
    }

    override fun attach(view: LoginContract.View) {
        mView = view
    }

    override fun submitUserName(userName: String) {
        mView?.showProgress()
        var email = userName
        if (StringUtils.isPhoneNumber(userName))
            email = getEmail(email)
        mUserInformationRepository.findByUsername(email)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({information ->
                if (information == null) {
                    allowRegister(userName)
                } else {
                    mView?.allowLogin(information)
                    mView?.hideProgress()
                }
            }, {
                mView?.hideProgress()
            })
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
                    Log.d("xxx", p0.message.toString())
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
            register(UserInformation(email =  email, displayName =  displayName), password)
        }
        else if (StringUtils.isPhoneNumber(userName)) {
            val phoneNumber = userName
            register(UserInformation(email = getEmail(phoneNumber),
                displayName =  displayName,
                phoneNumber =  phoneNumber), password)
        }
    }

    private fun register(userInformation: UserInformation, password: String) {
        mView?.showProgress()
        mUserInformationRepository.register(
            email = userInformation.email,
            displayName =  userInformation.displayName,
            phoneNumber =  userInformation.phoneNumber,
            password = password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {success ->
                mView?.hideProgress()
                if (success) login(userInformation.email, password)
            }, {
                mView?.hideProgress()
            })
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