package com.greydog.cvsections

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.greydog.cvsections.repository.ContactInfoRepository
import com.greydog.datamodels.ContactInfo
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ContactInfoViewModelTest {
    lateinit var viewModel: ContactInfoViewModel
    lateinit var repository: ContactInfoRepository

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        repository = mockk(relaxed = true)

        every { repository.getContactInfo() }.returns(Observable.just(contactInfo))
    }

    @Test
    fun checkFields() {
        viewModel = ContactInfoViewModel(repository)

        Assert.assertEquals(contactInfo.name, viewModel.name.value)
        Assert.assertEquals(contactInfo.mobileContact, viewModel.mobile.value)
        Assert.assertEquals(contactInfo.emailAddress, viewModel.email.value)
        Assert.assertEquals(contactInfo.linkedin, viewModel.linkedIn.value)
        Assert.assertEquals(contactInfo.addressLines, viewModel.address.value)
    }

    val contactInfo: ContactInfo =
        ContactInfo().apply {
            name = "Ed Price"
            addressLines = listOf("123 This Street", "London", "SW17 6YT")
            mobileContact = "07123456789"
            emailAddress = "ed@greydogconsulting.co.uk"
            linkedin = "https://linkedin.com"
        }
}