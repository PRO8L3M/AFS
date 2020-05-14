package com.afs.mobile

import android.app.Application
import android.os.StrictMode
import com.afs.mobile.di.AppModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        setupKoin()
        setupTimber()
        setupStrictMode()
    }

    private fun setupKoin() = startKoin {
        androidContext(this@MyApplication)
        modules(AppModules.modules)
    }

    private fun setupTimber() = Timber.plant(DebugTree())

    private fun setupStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )
            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )
        }
    }
}