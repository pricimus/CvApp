package com.greydog.cvsections.screens

import com.greydog.cvsections.R
import com.greydog.cvsections.checkText

class ContactInfoScreen {
    private val name = R.id.name
    private val email = R.id.email
    private val mobile = R.id.mobile
    private val linkedin = R.id.linkedin

    fun checkNameText(text: String) {
        name.checkText(text)
    }

    fun checkEmailText(text: String) {
        email.checkText(text)
    }

    fun checkLinkedInText(text: String) {
        linkedin.checkText(text)
    }

    fun checkMobileNumber(text: String) {
        mobile.checkText(text)
    }

}