package com.example.minijobchat.ui.other

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.example.minijobchat.R
import kotlinx.android.synthetic.main.view_action_bar.view.*


class CustomActionBar(context: Context, attributeSet: AttributeSet): RelativeLayout(context) {
    init {
        LayoutInflater.from(context).inflate(R.layout.view_action_bar, this)
        titleTextView.text = ""

        val typeArray = context.obtainStyledAttributes(attributeSet, R.styleable.CustomActionBar, 0, 0)
        val iconRef = typeArray.getResourceId(R.styleable.CustomActionBar_icon_resource, R.drawable.ic_arrow_back_black_24dp)
        val title = typeArray.getString(R.styleable.CustomActionBar_title)

        iconImageView.setImageResource(iconRef)
        if (title != null) titleTextView.text = title

        iconImageView.setOnClickListener {
            if (context is Activity)
                context.finish()
        }

        typeArray.recycle()
    }

    fun setTitle(text: String) {
        titleTextView.text = text
    }

    fun setIcon(resId: Int) {
        iconImageView.setImageResource(resId)
    }

    fun setIconOnClickListener(listener: View.OnClickListener) {
        iconImageView.setOnClickListener(listener)
    }
}