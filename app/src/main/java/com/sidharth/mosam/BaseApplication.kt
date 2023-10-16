package com.sidharth.mosam

import android.app.Application
import com.sidharth.mosam.di.appModule
import com.sidharth.mosam.di.dbModule
import com.sidharth.mosam.di.networkingModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    companion object {
        lateinit var instance: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@BaseApplication)
            // Load modules
            modules(
                appModule(),
                dbModule(this@BaseApplication),
                networkingModule()
            )
        }
    }
}
