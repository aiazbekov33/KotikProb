package com.example.kotikprob

import android.app.Application
import com.example.kotikprob.servicelocator.networkModule
import com.example.kotikprob.servicelocator.repositoriesModel
import com.example.kotikprob.servicelocator.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(networkModule, repositoriesModel, viewModelModule)
        }
    }
}