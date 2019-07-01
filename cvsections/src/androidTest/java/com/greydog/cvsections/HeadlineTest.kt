package com.greydog.cvsections

import android.preference.PreferenceManager
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.platform.app.InstrumentationRegistry
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.junit.WireMockRule
import com.greydog.cvsections.screens.HeadlineScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HeadlineTest: BaseInstrumentedTest() {
    private val headlineScreen = HeadlineScreen()

    private val config : WireMockConfiguration = setupWireMockMappings()

    @get:Rule
    val wireMockRule = WireMockRule(config)

    @Before
    fun before() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putString("environment", "http://127.0.0.1:8080")
    }

    @Test
    fun checkContactDetailsLoad() {
        launchFragmentInContainer<HeadlineFragment>()

        headlineScreen.checkTitleText("Senior Android Developer")
        headlineScreen.checkAboutText("Experienced mobile developer")
        headlineScreen.checkTechnologiesText("Kotlin")
    }
}