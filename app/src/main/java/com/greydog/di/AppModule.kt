package com.greydog.di

import com.greydog.cvapp.MenuViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    viewModel { MenuViewModel() }
}