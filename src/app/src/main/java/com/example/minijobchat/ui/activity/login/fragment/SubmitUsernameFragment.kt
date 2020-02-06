package com.example.minijobchat.ui.activity.login.fragment


import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.minijobchat.R
import com.example.minijobchat.ui.activity.login.LoginActivityFragment
import com.example.minijobchat.utils.StringUtils
import com.example.minijobchat.utils.extension.hideKeyboard
import com.hbb20.CountryCodePicker
import kotlinx.android.synthetic.main.fragment_submit_username.*

/**
 * A simple [Fragment] subclass.
 */
class SubmitUsernameFragment : Fragment() {

    var activityFragment: LoginActivityFragment? = null
    lateinit var countryPicker: CountryCodePicker

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_submit_username, null, false)
        countryPicker = CountryCodePicker(view.context).apply {
            setAutoDetectedCountry(true)
            changeDefaultLanguage(CountryCodePicker.Language.VIETNAMESE)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nextButton.setOnClickListener {
            val text = usernameEditText.text.toString().replace(" ", "")

            this.activity?.hideKeyboard()

            if (text.isEmpty()) {
                errorTextView.visibility = View.VISIBLE
                errorTextView.text = getText(R.string.error_empty_email_or_phonenumber)
            }
            else if (StringUtils.isPhoneNumber(text)) {
                countryPicker.registerCarrierNumberEditText(usernameEditText)
                activityFragment?.onSubmitUserName(countryPicker.formattedFullNumber.replace(" ", ""))
                countryPicker.deregisterCarrierNumberEditText()
            }
            else if (StringUtils.isEmail(text)) {
                activityFragment?.onSubmitUserName(text)
            }
            else {
                errorTextView.visibility = View.VISIBLE
                errorTextView.text = getText(R.string.error_invalid_email_or_phonenumber)
            }
        }

        countryPicker.setOnCountryChangeListener {
            updateEndDrawableUsernameEditText(countryPicker.imageViewFlag.drawable)
        }

        usernameEditText.setOnTouchListener(object: View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                val DRAWABLE_RIGHT = 2;
                val drawable =  usernameEditText.getCompoundDrawables()[DRAWABLE_RIGHT]

                if (event?.action == MotionEvent.ACTION_UP && drawable != null) {
                    if(event.getRawX() >= (usernameEditText.getRight() - drawable.getBounds().width())) {
                        countryPicker.launchCountrySelectionDialog()
                        return true;
                    }
                }
                return false
            }
        })

        usernameEditText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val text = usernameEditText.text.toString()
                if (text.isNotEmpty() && StringUtils.isPhoneNumber(text))
                    updateEndDrawableUsernameEditText(countryPicker.imageViewFlag.drawable)
                else
                    updateEndDrawableUsernameEditText(null)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                errorTextView.visibility = View.GONE
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun updateEndDrawableUsernameEditText(drawable: Drawable?) {
        usernameEditText.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
    }
}
