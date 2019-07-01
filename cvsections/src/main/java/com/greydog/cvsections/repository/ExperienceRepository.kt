package com.greydog.cvsections.repository

import com.greydog.cvsections.repository.remote.CVSectionsAPIService
import com.greydog.datamodels.Experience
import io.reactivex.Observable

class ExperienceRepository(private val cvApiService : CVSectionsAPIService) {
    var experienceCache : List<Experience> = listOf()

    fun getExperience() : Observable<List<Experience>> {
        if (experienceCache.isEmpty()) {
            return cvApiService.getExperience()
                .doOnNext { experienceCache = it }
        }
        else {
            return Observable.just(experienceCache)
                .mergeWith(cvApiService.getExperience())
                .doOnNext { experienceCache = it  }
        }
    }
}