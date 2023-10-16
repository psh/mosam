package com.sidharth.mosam.di

import android.app.Application
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.sidharth.mosam.WeatherDatabase
import com.sidharth.mosam.data.local.LocalDataSource
import com.sidharth.mosam.data.local.WeatherDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(
    private val application: Application
) {
    @Provides
    @Singleton
    fun provideLocalDataSource(weatherDao: WeatherDao): LocalDataSource {
        return LocalDataSource(weatherDao)
    }

    @Provides
    @Singleton
    fun provideDatabaseDriver(): SqlDriver {
        return AndroidSqliteDriver(WeatherDatabase.Schema, application, "mosam_database.db")
    }

    @Provides
    fun provideSqlDelightDao(driver: SqlDriver) : WeatherDao {
        val queries = WeatherDatabase(driver).weatherQueries
        return WeatherDao(queries)
    }
}