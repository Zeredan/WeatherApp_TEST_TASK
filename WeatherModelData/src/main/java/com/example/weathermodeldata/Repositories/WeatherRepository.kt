package com.example.weathermodeldata.Repositories

import com.example.weathermodeldata.DataClasses.MainClasses.CurrentWeather
import com.example.weathermodeldata.DataClasses.MainClasses.DayWeather
import com.example.weathermodeldata.DataClasses.MainClasses.HourWeather
import com.example.weathermodeldata.DataSources.WeatherDataSource
import javax.inject.Inject


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