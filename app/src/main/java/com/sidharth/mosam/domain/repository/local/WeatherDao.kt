package com.sidharth.mosam.domain.repository.local

import com.sidharth.mosam.WeatherQueries

class WeatherDao(private val queries: WeatherQueries) {

    suspend fun insert(data: WeatherEntity) {
        queries.deleteAll()

        queries.insert(
            0, data.background, data.sunrise,
            data.sunset, data.temperature, data.feelsLike, data.pressure,
            data.humidity, data.visibility, data.uvi,
            data.windSpeed, data.windDegree, data.weather, data.forecasts
        )
    }

    suspend fun selectAll(): WeatherEntity? {
        val data = queries.queryAll().executeAsOneOrNull() ?: return null

        return WeatherEntity(
            data.id, data.background, data.sunrise, data.sunset,
            data.temperature, data.feelsLike, data.pressure, data.humidity,
            data.visibility, data.uvi, data.windSpeed, data.windDegree,
            data.weather, data.forecasts
        )
    }
}