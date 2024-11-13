package com.example.weathermodeldata.APIs

import com.example.weathermodeldata.APIs.Retrofit.DTO.toCurrentWeather
import com.example.weathermodeldata.APIs.Retrofit.DTO.toDayWeatherList
import com.example.weathermodeldata.APIs.Retrofit.DTO.toHourWeatherList
import com.example.weathermodeldata.APIs.Retrofit.RetrofitWeatherApi
import com.example.weathermodeldata.DataClasses.MainClasses.CurrentWeather
import com.example.weathermodeldata.DataClasses.MainClasses.DayWeather
import com.example.weathermodeldata.DataClasses.MainClasses.HourWeather

class WeatherApiRetrofitImplementation(val retrofitWeatherApi: RetrofitWeatherApi) : WeatherApi {
    override suspend fun getCurrentWeather(lat: Float, lon: Float, appId: String): CurrentWeather {
        println("IN_CUR_WEATHER_WARI")
        val response = retrofitWeatherApi.getCurrentData(
            mapOf(
                "lat" to lat.toString(),
                "lon" to lon.toString(),
                "appid" to appId
            )
        )
        val responseCurrentWeather = response.current
        return responseCurrentWeather.toCurrentWeather()
    }

    override suspend fun getHourWeather48(lat: Float, lon: Float, appId: String): List<HourWeather> {
        val response = retrofitWeatherApi.getCurrentData(
            mapOf(
                "lat" to lat.toString(),
                "lon" to lon.toString(),
                "appid" to appId
            )
        )
        val responseHourlyData = response.hourly
        return responseHourlyData.toHourWeatherList()
    }

    override suspend fun getDayWeather8(lat: Float, lon: Float, appId: String): List<DayWeather> {
        val response = retrofitWeatherApi.getCurrentData(
            mapOf(
                "lat" to lat.toString(),
                "lon" to lon.toString(),
                "appid" to appId
            )
        )
        val responseDailyData = response.daily
        return responseDailyData.toDayWeatherList()
    }

}