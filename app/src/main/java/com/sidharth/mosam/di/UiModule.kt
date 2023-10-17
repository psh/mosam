package com.sidharth.mosam.di

import com.sidharth.mosam.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun uiModule() = module {
    viewModel {
        WeatherViewModel(useCase = get())
    }
}