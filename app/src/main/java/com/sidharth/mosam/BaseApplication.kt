package com.sidharth.mosam

import android.app.Application
import android.content.Context
import com.sidharth.mosam.di.initializeDi
import com.sidharth.mosam.util.initLogging
import org.koin.dsl.module

class BaseApplication : Application() {
    companion object {
        lateinit var instance: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        initLogging()
        initializeDi(appModule())
    }

    private fun appModule() = module {
        single<Context> { this@BaseApplication }
    }
}
