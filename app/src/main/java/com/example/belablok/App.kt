package com.example.belablok

import android.app.Application
import android.content.Context
import com.example.belablok.di.repositoryModule
import com.example.belablok.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    companion object {
        lateinit var application : App
    }
    override fun onCreate() {
        super.onCreate()
        application = this
        startKoin {
            androidContext(this@App)
            modules(com.example.belablok.di.modules)
        }
    }
}