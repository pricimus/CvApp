package com.greydog.cvsections

import androidx.lifecycle.MutableLiveData

class ExperienceItemViewModel {

    val company : MutableLiveData<String> = MutableLiveData()
    val title : MutableLiveData<String> = MutableLiveData()
    val location : MutableLiveData<String> = MutableLiveData()
    val description : MutableLiveData<String> = MutableLiveData()
    val period : MutableLiveData<String> = MutableLiveData()
    val status : MutableLiveData<String> = MutableLiveData()
}