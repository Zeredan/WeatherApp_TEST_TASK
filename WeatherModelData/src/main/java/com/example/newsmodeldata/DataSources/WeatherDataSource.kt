package com.example.newsmodeldata.DataSources

import com.example.newsmodeldata.DataClasses.MainClasses.CurrentWeather
import com.example.newsmodeldata.DataClasses.MainClasses.DayWeather
import com.example.newsmodeldata.DataClasses.MainClasses.HourWeather

interface WeatherDataSource {
    suspend fun getCurrentWeather(lat: Float, lon: Float, appId: String) : CurrentWeather
    suspend fun getHourWeather48(lat: Float, lon: Float, appId: String) : List<HourWeather>
    suspend fun getDayWeather8(lat: Float, lon: Float, appId: String) : List<DayWeather>
}