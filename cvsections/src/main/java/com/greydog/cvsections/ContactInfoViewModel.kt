package com.greydog.cvsections

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.greydog.cvsections.repository.ContactInfoRepository
import com.greydog.datamodels.ContactInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ContactInfoViewModel(private val repository: ContactInfoRepository) : ViewModel() {
    val name : MutableLiveData<String> = MutableLiveData()
    val email : MutableLiveData<String> = MutableLiveData()
    val linkedIn : MutableLiveData<String> = MutableLiveData()
    val address : MutableLiveData<List<String>> = MutableLiveData()
    val mobile : MutableLiveData<String> = MutableLiveData()

    val contactInfo : MutableLiveData<ContactInfo> = MutableLiveData()

    private lateinit var subscription: Disposable

    init {
        load()
    }

    private fun load() {

        subscription = repository.getContactInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                contactInfo.value = it
                name.value = it?.name
                email.value = it?.emailAddress
                linkedIn.value = it?.linkedin
                address.value = it?.addressLines
                mobile.value = it?.mobileContact
            },Throwable::printStackTrace)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}
