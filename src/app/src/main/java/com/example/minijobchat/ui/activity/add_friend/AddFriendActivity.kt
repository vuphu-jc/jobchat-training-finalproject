package com.example.minijobchat.ui.activity.add_friend

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.minijobchat.R
import kotlinx.android.synthetic.main.activity_add_friend.*

class AddFriendActivity : AppCompatActivity() {

    companion object {
        fun getInstance(context: Context): Intent {
            return Intent(context, AddFriendActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_friend)

        initialize()
    }

    private fun initialize() {
//        actionBarCustomActionBar.setIconOnClickListener(View.OnClickListener {
//            finish()
//        })
    }
}
