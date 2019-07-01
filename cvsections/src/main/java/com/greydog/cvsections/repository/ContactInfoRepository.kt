package com.greydog.cvsections.repository

import com.greydog.cvsections.repository.remote.CVSectionsAPIService
import com.greydog.datamodels.ContactInfo
import io.reactivex.Observable

class ContactInfoRepository(private val cvApiService : CVSectionsAPIService) {
    var contactInfoCache : ContactInfo? = null

    fun getContactInfo() : Observable<ContactInfo?> {
        if (contactInfoCache == null) {
            return cvApiService.getContactInfo()
                .doOnNext { contactInfoCache = it }
        }
        else {
            return Observable.just(contactInfoCache)
                .mergeWith(cvApiService.getContactInfo())
                .doOnNext { contactInfoCache = it  }
        }
    }
}