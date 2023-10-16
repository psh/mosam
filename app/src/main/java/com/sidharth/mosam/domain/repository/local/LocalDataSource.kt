package com.sidharth.mosam.domain.repository.local

import com.sidharth.mosam.domain.EmptyWeatherData
import com.sidharth.mosam.domain.WeatherData

class LocalDataSource(
    private val weatherDao: WeatherDao
) {
    suspend fun upsertWeatherData(weatherData: WeatherData) {
        weatherDao.insert(
            mapWeatherDataToWeatherEntity(weatherData)
        )
    }

    suspend fun getWeatherData(): WeatherData {
        return weatherDao.selectAll()?.let {
            mapWeatherEntityToWeatherData(it)
        } ?: EmptyWeatherData.instance
    }
}