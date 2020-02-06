package com.example.minijobchat.ui.activity.login.fragment

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.example.minijobchat.R
import com.example.minijobchat.ui.activity.login.LoginActivityFragment
import com.example.minijobchat.utils.StringUtils
import com.example.minijobchat.utils.extension.hideKeyboard
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_register_phonenumber.*
import kotlinx.android.synthetic.main.view_register.*
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass.
 */
class RegisterPhoneNumberFragment : Fragment() {

    var activityFragment: LoginActivityFragment? = null
    lateinit var phoneNumber: String

    companion object {
        fun newInstance(activityFragment: LoginActivityFragment, phoneNumber: String): RegisterPhoneNumberFragment {
            return RegisterPhoneNumberFragment().apply {
                this.activityFragment = activityFragment
                this.phoneNumber = phoneNumber
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_phonenumber, null, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backButton.setOnClickListener {
            activityFragment?.onBackFragment()
        }
        phoneNumberTextView.text = phoneNumber
        registerButton.setOnClickListener {
            activity?.hideKeyboard()
            activityFragment?.onRegister(phoneNumber, passwordEditText.text.toString(), displayNameEditText.text.toString())
        }
    }
}
