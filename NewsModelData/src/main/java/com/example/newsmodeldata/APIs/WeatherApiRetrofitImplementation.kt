package com.example.newsmodeldata.APIs

import com.example.newsmodeldata.APIs.Retrofit.DTO.toCurrentWeather
import com.example.newsmodeldata.APIs.Retrofit.DTO.toDayWeatherList
import com.example.newsmodeldata.APIs.Retrofit.DTO.toHourWeatherList
import com.example.newsmodeldata.APIs.Retrofit.RetrofitWeatherApi
import com.example.newsmodeldata.DataClasses.MainClasses.CurrentWeather
import com.example.newsmodeldata.DataClasses.MainClasses.DayWeather
import com.example.newsmodeldata.DataClasses.MainClasses.HourWeather
import com.example.newsmodeldata.DataClasses.SupportingClasses.FeelsLike
import com.example.newsmodeldata.DataClasses.SupportingClasses.Temperature
import com.example.newsmodeldata.DataClasses.SupportingClasses.Weather

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