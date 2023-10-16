package com.sidharth.mosam.domain.usecase

import android.content.Context
import com.sidharth.mosam.domain.repository.WeatherDataRepository
import com.sidharth.mosam.domain.WeatherData

class GetWeatherDataUseCase(
    private val weatherDataRepository: WeatherDataRepository
)  {
    suspend operator fun invoke(
        context: Context,
        latitude: Double,
        longitude: Double
    ): WeatherData {
        return weatherDataRepository.getWeatherData(context, latitude, longitude)
    }
}