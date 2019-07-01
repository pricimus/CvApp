package com.greydog.cvsections.repository

import com.greydog.cvsections.repository.HeadlineRepository
import com.greydog.cvsections.repository.remote.CVSectionsAPIService
import com.greydog.datamodels.Headline
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import org.junit.Test
import org.junit.Before

class HeadlineRepositoryTest {
    private lateinit var cvSectionsAPIServiceMock: CVSectionsAPIService
    private lateinit var headlineRepository : HeadlineRepository

    @Before
    fun setup() {
        cvSectionsAPIServiceMock = mockk(relaxed = true)
        headlineRepository = HeadlineRepository(cvSectionsAPIServiceMock)
    }

    @Test
    fun whenCacheNullCallToRepositoryReturnsData() {
        val headline = Headline("Senior Android Developer")

        every { cvSectionsAPIServiceMock.getHeadline() }.returns(Observable.just(headline))

        val headlineReturned = headlineRepository.getHeadline()

        headlineReturned.test().assertValue { it == headline }
    }

    @Test
    fun whenCacheNotNullCallToRepositoryReturnsData() {
        val cachedHeadline = Headline("Senior Android Developer")
        val apiHeadline = Headline("Head of Development")

        headlineRepository.headlineCache = cachedHeadline

        every { cvSectionsAPIServiceMock.getHeadline() }.returns(Observable.just(apiHeadline))

        val experienceReturned = headlineRepository.getHeadline()

        experienceReturned.test().assertValueAt(0) { it == cachedHeadline}
        experienceReturned.test().assertValueAt(1) { it == apiHeadline}
    }
}