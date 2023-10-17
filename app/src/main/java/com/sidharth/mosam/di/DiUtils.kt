package com.sidharth.mosam.di

import co.touchlab.kermit.Logger
import co.touchlab.kermit.koin.KermitKoinLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun initializeDi(module: Module) {
    startKoin {
        logger(
            KermitKoinLogger(Logger.withTag("koin"))
        )

        // Load modules
        modules(
            module,
            driverModule(),
            repositoryModule(),
            networkingModule(),
            uiModule(),
        )
    }
}
