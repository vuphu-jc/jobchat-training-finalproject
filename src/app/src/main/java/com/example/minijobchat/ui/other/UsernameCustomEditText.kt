package com.example.minijobchat.ui.other

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import com.example.minijobchat.R
import com.example.minijobchat.utils.StringUtils
import com.example.minijobchat.utils.extension.gone
import com.example.minijobchat.utils.extension.visible
import com.hbb20.CountryCodePicker
import kotlinx.android.synthetic.main.fragment_submit_username.*
import kotlinx.android.synthetic.main.view_username_custom_edit_text.view.*

class UsernameCustomEditText(context: Context, attributeSet: AttributeSet): LinearLayout(context) {
    private lateinit var countryPicker: CountryCodePicker

    init {
        LayoutInflater.from(context).inflate(R.layout.view_username_custom_edit_text, this)
        countryPicker = CountryCodePicker(context).apply {
            setAutoDetectedCountry(true)
            changeDefaultLanguage(CountryCodePicker.Language.VIETNAMESE)
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
                errorTextView.gone()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun updateEndDrawableUsernameEditText(drawable: Drawable?) {
        usernameEditText.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
    }

    fun showError(message: String) {
        errorTextView.text = message
        errorTextView.visible()
    }
}