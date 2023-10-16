package com.sidharth.mosam

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sidharth.mosam.domain.WeatherData
import com.sidharth.mosam.domain.usecase.GetWeatherDataUseCase
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val useCase: GetWeatherDataUseCase
) : ViewModel() {
    private val _weatherData = MutableLiveData<WeatherData>()

    val weatherData: LiveData<WeatherData> get() = _weatherData

    fun getWeatherData(
        context: Context,
        latitude: Double,
        longitude: Double
    ) {
        viewModelScope.launch {
            val data: WeatherData = useCase(context, latitude, longitude)
            _weatherData.postValue(data)
        }
    }
}