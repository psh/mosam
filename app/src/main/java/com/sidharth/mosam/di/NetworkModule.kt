package com.sidharth.mosam.di

import com.sidharth.mosam.data.remote.RemoteDataSource
import com.sidharth.mosam.data.remote.WeatherService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.openweathermap.org/data/3.0/"

fun networkingModule() = module {
    single<WeatherService> {
        // Log the network calls
        val okClient = OkHttpClient.Builder()
            .addNetworkInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            ).build()

        val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        retrofit.create(WeatherService::class.java)
    }

    single {
        RemoteDataSource(weatherService = get())
    }
}
