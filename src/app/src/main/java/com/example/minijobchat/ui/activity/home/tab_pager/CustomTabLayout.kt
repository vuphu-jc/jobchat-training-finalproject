package com.example.minijobchat.ui.activity.home.tab_pager

import com.google.android.material.tabs.TabLayout

class CustomTabLayout(val tabLayout: TabLayout, icons: ArrayList<Int>) {
    init {
        for (i in 0 until tabLayout.tabCount) {
            val customView = CustomTabItem(tabLayout.context)
            customView.setIcon(icons[i])
            tabLayout.getTabAt(i)?.customView = customView
        }
        addOnTabSelectedListener()
    }

    private fun addOnTabSelectedListener() {
        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
                (p0?.customView as CustomTabItem).unselected()
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                (p0?.customView as CustomTabItem).selected()
            }
        })

        (tabLayout.getTabAt(0)?.customView as CustomTabItem).selected()
    }
}