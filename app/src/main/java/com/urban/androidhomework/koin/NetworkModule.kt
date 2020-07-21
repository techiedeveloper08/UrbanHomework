package com.urban.androidhomework.koin

import android.content.Context
import com.urban.androidhomework.api.NetworkApi
import com.urban.androidhomework.api.NetworkClient
import org.koin.dsl.module

val networkModule = module {
    single { provideService(get()) }
}

fun provideService(context: Context): NetworkApi {
    return NetworkClient.get().setup(context)
}