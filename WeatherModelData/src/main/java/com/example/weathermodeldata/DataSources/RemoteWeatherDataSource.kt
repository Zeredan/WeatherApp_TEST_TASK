package com.example.weathermodeldata.DataSources

import com.example.weathermodeldata.APIs.WeatherApi
import com.example.weathermodeldata.DataClasses.MainClasses.CurrentWeather
import com.example.weathermodeldata.DataClasses.MainClasses.DayWeather
import com.example.weathermodeldata.DataClasses.MainClasses.HourWeather
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class RemoteWeatherDataSource(
    val api: WeatherApi,
    val coroutineContext: CoroutineContext
) : WeatherDataSource {
    override suspend fun getCurrentWeather(lat: Float, lon: Float, appId: String): CurrentWeather {
        return withContext(coroutineContext){
            api.getCurrentWeather(lat, lon, appId)
        }
    }
    override suspend fun getDayWeather8(lat: Float, lon: Float, appId: String): List<DayWeather> {
        return withContext(coroutineContext){
            api.getDayWeather8(lat, lon, appId)
        }
    }

    override suspend fun getHourWeather48(lat: Float, lon: Float, appId: String): List<HourWeather> {
        return withContext(coroutineContext){
            api.getHourWeather48(lat, lon, appId)
        }
    }
}