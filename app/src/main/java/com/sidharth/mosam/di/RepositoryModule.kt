package com.sidharth.mosam.di

import com.sidharth.mosam.WeatherDatabase
import com.sidharth.mosam.domain.repository.WeatherDataRepository
import com.sidharth.mosam.domain.repository.local.LocalDataSource
import com.sidharth.mosam.domain.repository.local.WeatherDao
import com.sidharth.mosam.domain.repository.remote.RemoteDataSource
import com.sidharth.mosam.domain.usecase.GetWeatherDataUseCase
import org.koin.dsl.module


fun repositoryModule() = module {
    single {
        GetWeatherDataUseCase(weatherDataRepository = get())
    }

    single {
        WeatherDataRepository(
            localDataSource = get(), remoteDataSource = get()
        )
    }

    single {
        LocalDataSource(weatherDao = get())
    }

    single {
        WeatherDao(
            WeatherDatabase(driver = get()).weatherQueries
        )
    }

    single {
        RemoteDataSource(weatherService = get())
    }
}
