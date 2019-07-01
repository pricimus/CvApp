package com.greydog.cvsections

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.greydog.cvsections.repository.HeadlineRepository
import com.greydog.datamodels.Headline
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HeadlineViewModelTest {
    lateinit var viewModel: HeadlineViewModel
    lateinit var repository: HeadlineRepository

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        repository = mockk(relaxed = true)

        every { repository.getHeadline() }.returns(Observable.just(headline))
    }

    @Test
    fun checkFields() {
        viewModel = HeadlineViewModel(repository)

        Assert.assertEquals(headline.title, viewModel.title.value)
        Assert.assertEquals(headline.about, viewModel.about.value)
        Assert.assertEquals(headline.technologies, viewModel.technologies.value)
    }

    val headline: Headline =
        Headline().apply {
            title = "Senior Android Developer"
            about = "A senior android developer involved in multiple projects across multiple industries"
            technologies = "Kotlin, Java, AWS etc."
        }
}