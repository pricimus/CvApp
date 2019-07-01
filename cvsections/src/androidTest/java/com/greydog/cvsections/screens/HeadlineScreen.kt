package com.greydog.cvsections.screens

import com.greydog.cvsections.R
import com.greydog.cvsections.checkText

class HeadlineScreen {
    private val title = R.id.title
    private val about = R.id.about
    private val technologies = R.id.technologies

    fun checkTitleText(text: String) {
        title.checkText(text)
    }

    fun checkAboutText(text: String) {
        about.checkText(text)
    }

    fun checkTechnologiesText(text: String) {
        technologies.checkText(text)
    }
}