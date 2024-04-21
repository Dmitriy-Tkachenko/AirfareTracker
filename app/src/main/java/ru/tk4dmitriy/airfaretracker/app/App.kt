package ru.tk4dmitriy.airfaretracker.app

import android.app.Application
import android.content.Context
import ru.tk4dmitriy.airfaretracker.di.AppComponent
import ru.tk4dmitriy.airfaretracker.di.DaggerAppComponent

class App : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().build()
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {
        @Volatile
        lateinit var appContext: Context
            private set
    }
}