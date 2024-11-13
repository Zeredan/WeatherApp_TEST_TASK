package com.example.todayforecastfeature.ViewModels

import LocalStubWeatherDataSource
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savedoptionsmodeldata.CommonDI.DaggerSavedOptionsDataDIComponent
import com.example.weathermodeldata.DataClasses.MainClasses.CurrentWeather
import com.example.weathermodeldata.DataClasses.MainClasses.HourWeather
import com.example.todayforecastfeature.UseCases.GetCurrentAndHourlyWeatherUseCase.GetCurrentAndHourlyWeatherUseCase
import com.example.weathermodeldata.APIs.WeatherApiStubImplementation
import com.example.weathermodeldata.CommonDI.DaggerWeatherModelDataDIComponent
import com.example.weathermodeldata.Repositories.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

// no leaks bcs UI updates context on recomposition
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class TodayForecastViewModel(var context: Context) : ViewModel() {
    val currentWeatherSharedFlow = MutableSharedFlow<CurrentWeather?>(replay = 1, extraBufferCapacity = 0, onBufferOverflow = BufferOverflow.DROP_LATEST)
    val hourWeatherListSharedFlow = MutableSharedFlow<List<HourWeather>?>(replay = 1, extraBufferCapacity = 0, onBufferOverflow = BufferOverflow.DROP_LATEST)
    var loadingResult by mutableStateOf<Boolean?>(null) // null - loading, false - stub, true - no stub

    private val weatherDaggerComponent = DaggerWeatherModelDataDIComponent
        .builder()
        .addBaseUrl("https://api.openweathermap.org")
        .addCoroutineContext(Dispatchers.Default)
        .build()

    private val savedOptionsDaggerComponent = DaggerSavedOptionsDataDIComponent
        .builder()
        .addDbName("savedOptionsDatabase")
        .addAppContext(context)
        .build()

    init{
        loadData()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun loadData(){
        val getCurrentAndHourlyWeatherUseCase = GetCurrentAndHourlyWeatherUseCase(
            remoteWeatherRepository = weatherDaggerComponent.resolveWeatherRepository(),
            localStubWeatherRepository = WeatherRepository( // для заглушки не стал создавать Dagger
                LocalStubWeatherDataSource(
                    WeatherApiStubImplementation(
                        context
                    ),
                    Dispatchers.Default
                )
            ),
            savedOptionsDaggerComponent.resolveSavedOptionsRepository(),
            context
        )
        viewModelScope.launch {
            println("preparing...")

            val result = getCurrentAndHourlyWeatherUseCase.getData()
            currentWeatherSharedFlow.emit(result.current)
            hourWeatherListSharedFlow.emit(result.hourly)
            loadingResult = result.noStub

            println("OKAY...")
        }
    }
}