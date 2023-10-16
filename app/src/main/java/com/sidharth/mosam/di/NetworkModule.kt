package com.sidharth.mosam.di

import com.sidharth.mosam.data.remote.RemoteDataSource
import com.sidharth.mosam.data.remote.WeatherService
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module

fun networkingModule() = module {
    single {
        HttpClient(OkHttp) {
            engine {
                config {
                    followRedirects(true)
                }
                addNetworkInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
            }
        }
    }

    single {
        WeatherService(httpClient = get())
    }

    single {
        RemoteDataSource(weatherService = get())
    }
}
