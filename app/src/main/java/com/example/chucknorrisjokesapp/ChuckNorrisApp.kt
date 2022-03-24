package com.example.chucknorrisjokesapp

import android.app.Application
import com.example.chucknorrisjokesapp.di.networkModule
import com.example.chucknorrisjokesapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ChuckNorrisApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@ChuckNorrisApp)
            modules(listOf(networkModule, viewModelModule))
        }
    }
}