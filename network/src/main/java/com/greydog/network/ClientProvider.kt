package com.greydog.network

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import java.util.prefs.Preferences

class ClientProvider(val context: Context) {
    private val okHttpClient: OkHttpClient = makeHttpClient()
    val client: Retrofit = createClient()

    private fun createClient(): Retrofit {
        val moshiBuilder = Moshi.Builder()

        val prefs = PreferenceManager.getDefaultSharedPreferences(context)

        val baseURL = prefs.getString("environment", "https://europe-west1-cvapp-76aa9.cloudfunctions.net")

        return Retrofit.Builder()
            .addConverterFactory(
                MoshiConverterFactory.create(moshiBuilder.build())
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseURL)
            .client(okHttpClient)
            .build()
    }

    private fun makeHttpClient() =
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
}