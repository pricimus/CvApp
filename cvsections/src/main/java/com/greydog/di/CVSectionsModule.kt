package com.greydog.di

import com.greydog.cvsections.ContactInfoViewModel
import com.greydog.cvsections.ExperienceViewModel
import com.greydog.cvsections.HeadlineViewModel
import com.greydog.cvsections.repository.ContactInfoRepository
import com.greydog.cvsections.repository.ExperienceRepository
import com.greydog.cvsections.repository.HeadlineRepository
import com.greydog.cvsections.repository.remote.CVSectionsAPIService
import com.greydog.cvsections.repository.remote.CVSectionsApiEndpoints
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val cvSectionsModule = module {
    viewModel { HeadlineViewModel(get()) }
    viewModel { ExperienceViewModel(get()) }
    viewModel { ContactInfoViewModel(get()) }
    single { HeadlineRepository(get()) }
    single { ExperienceRepository(get()) }
    single { ContactInfoRepository(get()) }
    single { CVSectionsAPIService(get()) }
}