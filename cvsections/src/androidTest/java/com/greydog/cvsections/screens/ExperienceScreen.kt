package com.greydog.cvsections.screens

import com.greydog.cvsections.R
import com.greydog.cvsections.checkText

class ExperienceScreen {
    private val company = R.id.company

    fun checkCompanyText(text: String) {
        company.checkText(text)
    }
}