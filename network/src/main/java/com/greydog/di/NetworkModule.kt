package com.greydog.di

import com.greydog.network.ClientProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val networkModule = module {
    single { ClientProvider(androidContext().applicationContext) }
}