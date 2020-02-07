package com.example.minijobchat.ui.activity.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.minijobchat.ui.activity.MainActivity
import com.example.minijobchat.ui.activity.home.HomeActivity
import com.example.minijobchat.ui.activity.login.LoginActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        (Handler()).postDelayed({
            if (auth.currentUser == null) {
                val intent = Intent(this, LoginActivity::class.java);
                startActivity(intent)
            } else {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
            finish()
        }, 100)
    }
}
