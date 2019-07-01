package com.greydog.cvsections.repository.remote

import com.greydog.datamodels.ContactInfo
import com.greydog.datamodels.Experience
import com.greydog.datamodels.Headline
import com.greydog.network.ClientProvider
import io.reactivex.Observable

class CVSectionsAPIService(clientProvider: ClientProvider) {
    private val api = clientProvider.client.create(CVSectionsApiEndpoints::class.java)

    fun getHeadline() : Observable<Headline> = api.getHeadline()

    fun getContactInfo() : Observable<ContactInfo> = api.getContactInfo()

    fun getExperience() : Observable<List<Experience>> = api.getExperience()
}