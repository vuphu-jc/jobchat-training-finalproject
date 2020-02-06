package com.example.minijobchat.ui.activity.login.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.minijobchat.R
import com.example.minijobchat.ui.activity.login.LoginActivityFragment
import com.example.minijobchat.utils.extension.hideKeyboard
import kotlinx.android.synthetic.main.fragment_register_email.*
import kotlinx.android.synthetic.main.view_register.*

/**
 * A simple [Fragment] subclass.
 */
class RegisterEmailFragment : Fragment() {

    var activityFragment: LoginActivityFragment? = null
    lateinit var email: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_email, null, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backButton.setOnClickListener {
            activityFragment?.onBackFragment()
        }
        emailTextView.text = email
        registerButton.setOnClickListener {
            activity?.hideKeyboard()
            activityFragment?.onRegister(email, passwordEditText.text.toString(), displayNameEditText.text.toString())
        }
    }
}
