package com.sidharth.mosam

import android.app.Application
import android.content.Context
import com.sidharth.mosam.di.driverModule
import com.sidharth.mosam.di.networkingModule
import com.sidharth.mosam.di.repositoryModule
import com.sidharth.mosam.di.uiModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

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
            // Load modules
            modules(
                appModule(),
                driverModule(),
                repositoryModule(),
                networkingModule(),
                uiModule(),
            )
        }
    }

    private fun appModule() = module {
        single<Context> { this@BaseApplication }
    }
}
