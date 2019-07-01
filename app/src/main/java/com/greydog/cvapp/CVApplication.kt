package com.greydog.cvapp

import android.app.Application
import com.greydog.di.appModule
import com.greydog.di.cvSectionsModule
import com.greydog.di.networkModule
import org.koin.android.ext.android.startKoin

class CVApplication : Application() {
    override fun onCreate(){
        super.onCreate()

        val modules = listOf(appModule, networkModule, cvSectionsModule)

        startKoin(this, modules)
    }
}