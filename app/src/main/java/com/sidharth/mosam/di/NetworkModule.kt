package com.sidharth.mosam.di

import co.touchlab.kermit.Logger
import com.sidharth.mosam.domain.repository.remote.WeatherService
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
                    HttpLoggingInterceptor { message ->
                        Logger.withTag("HttpClient").d(message)
                    }.apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
            }
        }
    }

    single {
        WeatherService(httpClient = get())
    }
}
