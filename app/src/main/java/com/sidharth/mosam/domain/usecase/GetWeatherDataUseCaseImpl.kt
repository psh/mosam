package com.sidharth.mosam.domain.usecase

import android.content.Context
import com.sidharth.mosam.domain.model.WeatherData
import com.sidharth.mosam.domain.repository.WeatherDataRepository

class GetWeatherDataUseCaseImpl(
    private val weatherDataRepository: WeatherDataRepository
) : GetWeatherDataUseCase {
    override suspend fun execute(
        context: Context,
        latitude: Double,
        longitude: Double
    ): WeatherData {
        return weatherDataRepository.getWeatherData(context, latitude, longitude)
    }
}