package com.sidharth.mosam.data.local

import com.sidharth.mosam.domain.model.EmptyWeatherData
import com.sidharth.mosam.domain.model.WeatherData

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