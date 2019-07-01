package com.greydog.cvsections

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.greydog.cvsections.repository.HeadlineRepository
import com.greydog.datamodels.Headline
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HeadlineViewModel(private val repository: HeadlineRepository) : ViewModel() {
    val title : MutableLiveData<String> = MutableLiveData()
    val about : MutableLiveData<String> = MutableLiveData()
    val technologies : MutableLiveData<String> = MutableLiveData()

    val headline : MutableLiveData<Headline> = MutableLiveData()

    private lateinit var subscription: Disposable

    init {
        load()
    }

    private fun load() {

        subscription = repository.getHeadline()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                headline.value = it
                title.value = it?.title
                about.value = it?.about
                technologies.value = it?.technologies
            },Throwable::printStackTrace)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}