package com.sidharth.mosam.data.remote

import com.sidharth.mosam.domain.model.EmptyWeatherData
import com.sidharth.mosam.domain.model.WeatherData

class RemoteDataSource(
    private val weatherService: WeatherService
) {
    suspend fun getWeatherData(
        latitude: Double,
        longitude: Double
    ): WeatherData {
        val response = weatherService.getWeatherData(latitude, longitude)
        return if (response != null) {
            WeatherResponseMapper.mapWeatherResponseToWeatherData(response)
        } else {
            EmptyWeatherData.instance
        }
    }
}