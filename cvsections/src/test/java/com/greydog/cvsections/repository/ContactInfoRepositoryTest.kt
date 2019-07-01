package com.greydog.cvsections.repository

import com.greydog.cvsections.repository.ContactInfoRepository
import com.greydog.cvsections.repository.remote.CVSectionsAPIService
import com.greydog.datamodels.ContactInfo
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import org.junit.Test
import org.junit.Before

class ContactInfoRepositoryTest {
    private lateinit var cvSectionsAPIServiceMock: CVSectionsAPIService
    private lateinit var contactInfoRepository : ContactInfoRepository

    @Before
    fun setup() {
        cvSectionsAPIServiceMock = mockk(relaxed = true)
        contactInfoRepository = ContactInfoRepository(cvSectionsAPIServiceMock)
    }

    @Test
    fun whenCacheNullCallToRepositoryReturnsData() {
        val contactInfo = ContactInfo("Ed Price")

        every { cvSectionsAPIServiceMock.getContactInfo() }.returns(Observable.just(contactInfo))

        val contactInfoReturned = contactInfoRepository.getContactInfo()

        contactInfoReturned.test().assertValue { it == contactInfo }
    }

    @Test
    fun whenCacheNotNullCallToRepositoryReturnsData() {
        val cachedContactInfo = ContactInfo("Ed Price")
        val apiContactInfo = ContactInfo("Bob API")

        contactInfoRepository.contactInfoCache = cachedContactInfo

        every { cvSectionsAPIServiceMock.getContactInfo() }.returns(Observable.just(apiContactInfo))

        val contactInfoReturned = contactInfoRepository.getContactInfo()

        contactInfoReturned.test().assertValueAt(0) { it == cachedContactInfo}
        contactInfoReturned.test().assertValueAt(1) { it == apiContactInfo}
    }
}