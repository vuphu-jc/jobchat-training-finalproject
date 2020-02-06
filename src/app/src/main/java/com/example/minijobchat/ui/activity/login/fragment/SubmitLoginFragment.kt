package com.example.minijobchat.ui.activity.login.fragment


import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.amulyakhare.textdrawable.TextDrawable
import com.example.minijobchat.R
import com.example.minijobchat.model.dto.User
import com.example.minijobchat.ui.activity.login.LoginActivityFragment
import com.example.minijobchat.utils.extension.hideKeyboard
import com.example.minijobchat.utils.extension.showKeyboard
import kotlinx.android.synthetic.main.fragment_submit_login.*


/**
 * A simple [Fragment] subclass.
 */
class SubmitLoginFragment : Fragment() {

    var activityFragment: LoginActivityFragment? = null
    lateinit var user: User
    private val DELAY_TIME: Long = 200
    private val AT_LEAST_CHARACTER = 6

    companion object {
        fun newInstance(activityFragment: LoginActivityFragment, user: User): SubmitLoginFragment {
            return SubmitLoginFragment().apply {
                this.activityFragment = activityFragment
                this.user = user
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_submit_login, null, false);
    }

    private fun createTextDrawable(view: View, char: String): TextDrawable {
        var bgColor = context?.getColor(R.color.colorPrimary)
        if (bgColor == null) bgColor = Color.CYAN
        return TextDrawable.builder()
            .beginConfig()
            .width(view.context.resources.getDimension(R.dimen.avatar_size).toInt())
            .height(view.context.resources.getDimension(R.dimen.avatar_size).toInt())
            .endConfig()
            .buildRect(char, bgColor)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backButton.setOnClickListener {
            activity?.hideKeyboard()
            activityFragment?.onBackFragment()
        }
        usernameTextView.text = user.displayName
        if (user.photoUrl.isEmpty()) {
            val drawable = createTextDrawable(view, user.displayName[0].toString())
            avatarCircleImageView.setImageDrawable(drawable)
        }

        (Handler()).postDelayed({
            activity?.showKeyboard(passwordEditText)
        }, DELAY_TIME)

        signInButton.setOnClickListener {
            val password = passwordEditText.text.toString()
            if (password.length < AT_LEAST_CHARACTER) {
                errorTextView.visibility = View.VISIBLE
                errorTextView.text = getString(R.string.error_password_at_least_6_characters)
            } else {
                activity?.hideKeyboard()
                activityFragment?.onSubmitLogin(user.email, passwordEditText.text.toString())
            }
        }

        passwordEditText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                errorTextView.visibility = View.GONE
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
    }
}
