package com.sidharth.mosam.di

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.sidharth.mosam.WeatherDatabase
import org.koin.dsl.module

fun driverModule() = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            WeatherDatabase.Schema,
            get<Context>(),
            "mosam_database.db"
        )
    }
}
