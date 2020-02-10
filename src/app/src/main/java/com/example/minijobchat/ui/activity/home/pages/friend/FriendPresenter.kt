package com.example.minijobchat.ui.activity.home.pages.friend

import com.example.minijobchat.model.repository.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FriendPresenter: FriendContract.Presenter {
    lateinit var userRepository: UserRepository

    private var mView: FriendContract.View? = null
    private val mCompositeDisposable = CompositeDisposable()

    override fun attach(view: FriendContract.View) {
        mView = view
    }

    override fun detach() {
        mView = null
        mCompositeDisposable.clear()
    }

    override fun findUserByUsername(userName: String) {
        mView?.showProgress()
        val disposable = userRepository.findUserByUsername(userName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({user ->
                if (user != null)
                    mView?.onFindUserSuccess(user)
                else
                    mView?.onNotFoundUser()
            }, {
                mView?.onError(it.message)
                mView?.hideProgress()
            })
        mCompositeDisposable.add(disposable)
    }

    override fun makeFriend(uid: String) {
//        val disposable = userRepository.makeFriend(uid)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                mView?.onMakeFriendSuccess()
//                mView?.hideProgress()
//            }, {
//                mView?.onError(it.message)
//                mView?.hideProgress()
//            })
//        mCompositeDisposable.add(disposable)
    }

}