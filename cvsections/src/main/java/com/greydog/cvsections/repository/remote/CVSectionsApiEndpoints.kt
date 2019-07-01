package com.greydog.cvsections.repository.remote

import com.greydog.datamodels.ContactInfo
import com.greydog.datamodels.Experience
import com.greydog.datamodels.Headline
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface CVSectionsApiEndpoints {
    @GET("/headline")
    fun getHeadline(): Observable<Headline>

    @GET("/contactdetails")
    fun getContactInfo(): Observable<ContactInfo>

    @GET("/experience")
    fun getExperience(): Observable<List<Experience>>
}