package com.example.minijobchat.ui.activity.home.pages.friend


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.minijobchat.R
import com.example.minijobchat.model.dto.User
import com.example.minijobchat.ui.activity.add_friend.AddFriendActivity
import com.example.minijobchat.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_friend.*

/**
 * A simple [Fragment] subclass.
 */
class FriendFragment : BaseFragment(), FriendContract.View {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friend, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addFriendButton.setOnClickListener {
            startActivity(AddFriendActivity.getInstance(view.context))
        }
    }

    override fun onFindUserSuccess(user: User) {
    }

    override fun onNotFoundUser() {
    }

    override fun onMakeFriendSuccess() {
    }
}
