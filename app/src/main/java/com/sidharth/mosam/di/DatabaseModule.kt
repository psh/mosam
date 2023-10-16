package com.sidharth.mosam.di

import android.app.Application
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.sidharth.mosam.WeatherDatabase
import com.sidharth.mosam.data.local.LocalDataSource
import com.sidharth.mosam.data.local.WeatherDao
import org.koin.dsl.module

fun dbModule(application: Application) = module {
    single {
        LocalDataSource(weatherDao = get())
    }

    single<SqlDriver> {
        AndroidSqliteDriver(
            WeatherDatabase.Schema,
            application,
            "mosam_database.db"
        )
    }

    single {
        WeatherDao(
            WeatherDatabase(driver = get()).weatherQueries
        )
    }
}
