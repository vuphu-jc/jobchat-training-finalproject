package com.example.minijobchat.ui.activity.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.minijobchat.R
import com.example.minijobchat.ui.activity.home.pages.account.AccountFragment
import com.example.minijobchat.ui.activity.home.pages.friend.FriendFragment
import com.example.minijobchat.ui.activity.home.pages.home.HomeFragment
import com.example.minijobchat.ui.activity.home.pages.message.MessageFragment
import com.example.minijobchat.ui.activity.home.pages.notification.NotificationFragment
import com.example.minijobchat.ui.activity.home.tab_pager.CustomFragmentPagerAdapter
import com.example.minijobchat.ui.activity.home.tab_pager.CustomTabLayout
import com.example.minijobchat.utils.extension.setHeightAnimation
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

    companion object {
        private const val DURATION_ANIMATION: Long = 300
        fun getInstance(context: Context): Intent {
            val intent = Intent(context, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            return intent
        }
    }

    private lateinit var mCustomTabLayout: CustomTabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initialize()
    }

    private fun initialize() {
        val mIcons = arrayListOf(
            R.drawable.ic_home,
            R.drawable.ic_message,
            R.drawable.ic_group,
            R.drawable.ic_notifications,
            R.drawable.ic_account)

        val mPages = arrayListOf(
            HomeFragment(),
            MessageFragment(),
            FriendFragment(),
            NotificationFragment(),
            AccountFragment()
        )

        val adapter = CustomFragmentPagerAdapter(this, supportFragmentManager, mPages)
        mainViewPager.adapter = adapter
        tabsTabLayout.setupWithViewPager(mainViewPager)
        mCustomTabLayout = CustomTabLayout(tabsTabLayout, mIcons)

        initializeAnimation()
    }

    private fun initializeAnimation() {
        tabsTabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val pos = tab?.position
                if (pos != null && pos == 0) {
                    headerRelativeLayout.setHeightAnimation(0, DURATION_ANIMATION)
                }
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val pos = tab?.position
                if (pos != null && pos == 0) {
                    headerRelativeLayout.setHeightAnimation(resources.getDimension(R.dimen.header_height).toInt(), DURATION_ANIMATION)
                }
            }

        })
    }
}
