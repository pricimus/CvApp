package com.greydog.cvsections.repository

import com.greydog.cvsections.repository.remote.CVSectionsAPIService
import com.greydog.datamodels.Headline
import io.reactivex.Observable

class HeadlineRepository(private val cvApiService : CVSectionsAPIService) {
    var headlineCache : Headline? = null

    fun getHeadline() : Observable<Headline?> {
        if (headlineCache == null) {
            return cvApiService.getHeadline()
                .doOnNext { headlineCache = it }
        }
        else {
            return Observable.just(headlineCache)
                .mergeWith(cvApiService.getHeadline())
                .doOnNext { headlineCache = it  }
        }
    }
}