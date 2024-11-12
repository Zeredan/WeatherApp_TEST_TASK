package com.example.newsmodeldata.Repositories

import com.example.newsmodeldata.DataClasses.MainClasses.CurrentWeather
import com.example.newsmodeldata.DataClasses.MainClasses.DayWeather
import com.example.newsmodeldata.DataClasses.MainClasses.HourWeather
import com.example.newsmodeldata.DataSources.WeatherDataSource
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class WeatherRepository @Inject constructor(
    private val weatherDataSource: WeatherDataSource,
)
{
    suspend fun getCurrentWeather(lat: Float, lon: Float, appId: String) : CurrentWeather{
        return weatherDataSource.getCurrentWeather(lat, lon, appId)
    }

    suspend fun getHourWeather48(lat: Float, lon: Float, appId: String) : List<HourWeather>{
        return weatherDataSource.getHourWeather48(lat, lon, appId)
    }

    suspend fun getDayWeather8(lat: Float, lon: Float, appId: String) : List<DayWeather>{
        return weatherDataSource.getDayWeather8(lat, lon, appId)
    }
}