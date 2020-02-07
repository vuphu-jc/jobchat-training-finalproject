package com.example.minijobchat.ui.activity.home.tab_pager

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import com.example.minijobchat.R
import kotlinx.android.synthetic.main.view_custom_tab_layout.view.*

class CustomTabItem(context: Context) : FrameLayout(context) {

    init {
        inflate(context, R.layout.view_custom_tab_layout, this)
        hideNotification()
    }

    fun setIcon(resId: Int) {
        iconImageView.setImageResource(resId)
    }

    fun selected() {
        iconImageView.setColorFilter(context.getColor(R.color.colorSelectedIcon))
    }

    fun unselected() {
        iconImageView.colorFilter = null
    }

    fun showNotification(number: Int) {
        numberTextView.text = number.toString()
        notificationRelativeLayout.visibility = View.VISIBLE
    }

    fun hideNotification() {
        notificationRelativeLayout.visibility = View.GONE
    }
}