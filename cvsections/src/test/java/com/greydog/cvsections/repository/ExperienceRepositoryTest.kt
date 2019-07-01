package com.greydog.cvsections.repository

import com.greydog.cvsections.repository.ExperienceRepository
import com.greydog.cvsections.repository.remote.CVSectionsAPIService
import com.greydog.datamodels.Experience
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import org.junit.Test
import org.junit.Before

class ExperienceRepositoryTest {
    private lateinit var cvSectionsAPIServiceMock: CVSectionsAPIService
    private lateinit var experienceRepository : ExperienceRepository

    @Before
    fun setup() {
        cvSectionsAPIServiceMock = mockk(relaxed = true)
        experienceRepository = ExperienceRepository(cvSectionsAPIServiceMock)
    }

    @Test
    fun whenCacheNullCallToRepositoryReturnsData() {
        val experience = listOf(Experience("Just Eat"))

        every { cvSectionsAPIServiceMock.getExperience() }.returns(Observable.just(experience))

        val experienceList = experienceRepository.getExperience()

        experienceList.test().assertValue { it == experience }
    }

    @Test
    fun whenCacheNotNullCallToRepositoryReturnsData() {
        val cachedExperience = listOf(Experience("Just Eat"))
        val apiExperience = listOf(Experience("Investec Private Bank"))

        experienceRepository.experienceCache = cachedExperience

        every { cvSectionsAPIServiceMock.getExperience() }.returns(Observable.just(apiExperience))

        val experienceReturned = experienceRepository.getExperience()

        experienceReturned.test().assertValueAt(0) { it == cachedExperience}
        experienceReturned.test().assertValueAt(1) { it == apiExperience}
    }
}