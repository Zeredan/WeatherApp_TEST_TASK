package com.example.weekforecastfeature.ViewModels

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
import com.example.weathermodeldata.APIs.WeatherApiStubImplementation
import com.example.weathermodeldata.CommonDI.DaggerWeatherModelDataDIComponent
import com.example.weathermodeldata.DataClasses.MainClasses.DayWeather
import com.example.weathermodeldata.Repositories.WeatherRepository
import com.example.weekforecastfeature.UseCases.GetDailyWeatherUseCase.GetDailyWeatherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

// no leaks bcs UI updates context on recomposition
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class WeekForecastViewModel(var context: Context) : ViewModel() {
    val dayWeatherListSharedFlow = MutableSharedFlow<List<DayWeather>?>(replay = 1, extraBufferCapacity = 0, onBufferOverflow = BufferOverflow.DROP_LATEST)
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
        val getDailyWeatherUseCase = GetDailyWeatherUseCase(
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
            loadingResult = null
            println("preparing...")

            val result = getDailyWeatherUseCase.getData()
            dayWeatherListSharedFlow.emit(result.daily)
            loadingResult = result.noStub

            println("OKAY...")
        }
    }
}