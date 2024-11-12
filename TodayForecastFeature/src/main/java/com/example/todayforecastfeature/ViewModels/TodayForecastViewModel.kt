package com.example.todayforecastfeature.ViewModels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsmodeldata.DataClasses.MainClasses.CurrentWeather
import com.example.newsmodeldata.DataClasses.MainClasses.HourWeather
import com.example.todayforecastfeature.ViewModels.UseCases.GetCurrentAndHourlyWeatherUseCase.GetCurrentAndHourlyWeatherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

// no leaks bcs UI updates context on recomposition
class TodayForecastViewModel(var context: Context) : ViewModel() {
    val currentWeatherSharedFlow = MutableSharedFlow<CurrentWeather?>(replay = 1, extraBufferCapacity = 0, onBufferOverflow = BufferOverflow.DROP_LATEST)
    val hourWeatherListSharedFlow = MutableSharedFlow<List<HourWeather>?>(replay = 1, extraBufferCapacity = 0, onBufferOverflow = BufferOverflow.DROP_LATEST)
    var loadingResult by mutableStateOf<Boolean?>(null) // null - loading, false - stub, true - no stub


    init{
        loadData()
    }

    fun loadData(){
        val getCurrentAndHourlyWeatherUseCase = GetCurrentAndHourlyWeatherUseCase(
            baseUrl = "https://api.openweathermap.org",
            coroutineContext = Dispatchers.Default,
            context = context
        )
        viewModelScope.launch {
            println("preparing...")
            delay(2000)

            val result = getCurrentAndHourlyWeatherUseCase.getData()
            currentWeatherSharedFlow.emit(result.current)
            hourWeatherListSharedFlow.emit(result.hourly)
            loadingResult = result.noStub

            println("OKAY...")
        }
    }
}