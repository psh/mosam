package com.sidharth.mosam.di

import com.sidharth.mosam.data.repository.WeatherDataRepositoryImpl
import com.sidharth.mosam.domain.repository.WeatherDataRepository
import com.sidharth.mosam.domain.usecase.GetWeatherDataUseCase
import com.sidharth.mosam.domain.usecase.GetWeatherDataUseCaseImpl
import com.sidharth.mosam.ui.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


fun appModule() = module {

    single<WeatherDataRepository> {
        WeatherDataRepositoryImpl(
            localDataSource = get(), remoteDataSource = get()
        )
    }

    single<GetWeatherDataUseCase> {
        GetWeatherDataUseCaseImpl(weatherDataRepository = get())
    }

    viewModel {
        WeatherViewModel(getWeatherDataUseCase = get())
    }
}
