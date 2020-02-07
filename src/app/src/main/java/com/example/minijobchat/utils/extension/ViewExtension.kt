package com.example.minijobchat.utils.extension

import android.animation.ValueAnimator
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_home.*

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.setHeightAnimation(height: Int, duration: Long) {
    val anim = ValueAnimator.ofInt(this.measuredHeight, height)
    anim.addUpdateListener { valueAnimator ->
        val value = valueAnimator.animatedValue as Int
        val layoutParams: ViewGroup.LayoutParams = this.layoutParams
        layoutParams.height = value
        this.layoutParams = layoutParams
    }
    anim.duration = duration
    anim.start()
}