package com.example.minijobchat.ui.activity.home.tab_pager

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.minijobchat.R
import com.example.minijobchat.ui.activity.home.pages.account.AccountFragment
import com.example.minijobchat.ui.activity.home.pages.friend.FriendFragment
import com.example.minijobchat.ui.activity.home.pages.home.HomeFragment
import com.example.minijobchat.ui.activity.home.pages.message.MessageFragment
import com.example.minijobchat.ui.activity.home.pages.notification.NotificationFragment
import com.google.android.material.tabs.TabLayout

class CustomFragmentPagerAdapter
    (private val context: Context,
     fragmentManager: FragmentManager, val fragments: ArrayList<Fragment>): FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}