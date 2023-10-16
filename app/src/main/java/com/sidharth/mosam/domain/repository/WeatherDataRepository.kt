package com.sidharth.mosam.domain.repository

import android.content.Context
import com.sidharth.mosam.domain.repository.local.LocalDataSource
import com.sidharth.mosam.domain.repository.remote.RemoteDataSource
import com.sidharth.mosam.domain.WeatherData
import com.sidharth.mosam.util.isNetworkConnected

class WeatherDataRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getWeatherData(
        context: Context,
        latitude: Double,
        longitude: Double
    ): WeatherData {
        return if (isNetworkConnected(context)) {
            val weatherData = remoteDataSource.getWeatherData(latitude, longitude)
            localDataSource.upsertWeatherData(weatherData)
            weatherData
        } else {
            localDataSource.getWeatherData()
        }
    }
}