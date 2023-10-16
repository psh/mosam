package com.sidharth.mosam.data.local

data class WeatherEntity(
    val id: Long = 0,
    val background: Long,
    val sunrise: String,
    val sunset: String,
    val temperature: Double,
    val feelsLike: Double,
    val pressure: Long,
    val humidity: Long,
    val visibility: Long,
    val uvi: Double,
    val windSpeed: Double,
    val windDegree: Long,
    val weather: String,
    val forecasts: String
)