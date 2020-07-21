package com.urban.androidhomework

import android.app.Application
import com.urban.androidhomework.api.NetworkClient
import com.urban.androidhomework.koin.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class UrbanHomeworkApp : Application() {
    override fun onCreate() {
        super.onCreate()
        NetworkClient.get().setup(applicationContext)

        val listOfModule = arrayListOf(networkModule)

        startKoin {
            androidLogger()
            androidContext(this@UrbanHomeworkApp)
            modules(listOfModule)
        }
    }
}