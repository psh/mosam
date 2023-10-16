package com.sidharth.mosam.domain.repository.remote

import com.google.gson.Gson
import com.sidharth.mosam.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

private const val BASE_URL = "https://api.openweathermap.org/data/3.0/"

class WeatherService(private val httpClient: HttpClient) {
    suspend fun getWeatherData(
        latitude: Double,
        longitude: Double,
    ): WeatherResponse? {
        val response: HttpResponse = httpClient.get("${BASE_URL}onecall") {
            url {
                parameters.append("lat", latitude.toString())
                parameters.append("lon", longitude.toString())
                parameters.append("units", "metric")
                parameters.append("exclude", "minutely,hourly")
                parameters.append("appid", BuildConfig.API_KEY)
            }
        }
        return Gson().fromJson(
            response.body<String>(), WeatherResponse::class.java
        )
    }
}