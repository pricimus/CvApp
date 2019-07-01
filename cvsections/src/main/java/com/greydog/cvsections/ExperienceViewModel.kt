package com.greydog.cvsections

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.greydog.cvsections.repository.ExperienceRepository
import com.greydog.datamodels.Experience
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ExperienceViewModel(private val repository: ExperienceRepository) : ViewModel() {
    val experienceListAdapter: ExperienceListAdapter = ExperienceListAdapter()

    val experience : MutableLiveData<List<Experience>> = MutableLiveData()

    private lateinit var subscription: Disposable

    init {
        load()
    }

    private fun load() {
        subscription = repository.getExperience()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                experience.value = it

        },Throwable::printStackTrace)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}