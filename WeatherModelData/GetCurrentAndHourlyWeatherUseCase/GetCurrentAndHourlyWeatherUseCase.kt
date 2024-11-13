package com.example.todayforecastfeature.ViewModels.UseCases.GetCurrentAndHourlyWeatherUseCase

import LocalStubWeatherDataSource
import android.content.Context
import com.example.newsmodeldata.APIs.WeatherApiStubImplementation
import com.example.newsmodeldata.CommonDI.DaggerWeatherModelDataDIComponent
import com.example.newsmodeldata.DataClasses.MainClasses.CurrentWeather
import com.example.newsmodeldata.DataClasses.MainClasses.HourWeather
import com.example.newsmodeldata.Repositories.WeatherRepository
import kotlin.coroutines.CoroutineContext

class GetCurrentAndHourlyWeatherUseCase(
    val coroutineContext: CoroutineContext,
    val baseUrl: String,
    context: Context
)
{
    private val remoteWeatherRepository: WeatherRepository
    private val localStubWeatherRepository: WeatherRepository
    //private val savedOptionsRepository: SavedOptionsRepository
    init{

        val daggerWeatherAppComponent = DaggerWeatherModelDataDIComponent
            .builder()
            .addBaseUrl(baseUrl)
            .addCoroutineContext(coroutineContext)
            .build()
        remoteWeatherRepository = daggerWeatherAppComponent.resolveWeatherRepository()
        localStubWeatherRepository = WeatherRepository(
            LocalStubWeatherDataSource(
                WeatherApiStubImplementation(
                    context
                ),
                coroutineContext
            )
        )
    }

    suspend fun getData() : CurrentAndHourlyWeatherResult{
        return try{
            throw Exception("test")
            CurrentAndHourlyWeatherResult(
                true,
                remoteWeatherRepository.getCurrentWeather(33.44f, -94.04f, "763e232f71d6d1b00s08b57135da2ab5f"),
                remoteWeatherRepository.getHourWeather48(33.44f, -94.04f, "763e232f71d6d1b0008b57135da2ab5f")
            )
        }
        catch (e: Exception){
            CurrentAndHourlyWeatherResult(
                false,
                localStubWeatherRepository.getCurrentWeather(33.44f, -94.04f, "763e232f71d6d1b00s08b57135da2ab5f"),
                localStubWeatherRepository.getHourWeather48(33.44f, -94.04f, "763e232f71d6d1b0008b57135da2ab5f")
            )
        }
    }
}