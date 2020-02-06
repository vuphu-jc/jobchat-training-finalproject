package com.example.minijobchat.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.minijobchat.R
import com.example.minijobchat.ui.activity.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        helloTextView.text = FirebaseAuth.getInstance().currentUser?.displayName

        signOutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(LoginActivity.getInstance(this))
        }
    }
}
