package com.sidharth.mosam.di.module

import com.sidharth.mosam.data.remote.RemoteDataSource
import com.sidharth.mosam.data.remote.WeatherService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/data/3.0/"
    }

    @Provides
    @Singleton
    fun provideWeatherService(): WeatherService {
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

        val weatherService: WeatherService by lazy {
            retrofit.create(WeatherService::class.java)
        }

        return weatherService
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(weatherService: WeatherService): RemoteDataSource {
        return RemoteDataSource(weatherService)
    }
}