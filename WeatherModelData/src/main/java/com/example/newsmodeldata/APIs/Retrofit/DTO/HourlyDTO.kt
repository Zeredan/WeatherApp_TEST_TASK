package com.example.newsmodeldata.APIs.Retrofit.DTO

import com.example.newsmodeldata.DataClasses.MainClasses.HourWeather
import com.example.newsmodeldata.DataClasses.SupportingClasses.Weather

data class HourlyDTO(
    val dt: Int?,
    val temp: Float?,
    val feels_like: Float?,
    val pressure: Int?,
    val humidity: Int?,
    val dew_point: Float?,
    val uvi: Int?,
    val clouds: Int?,
    val visibility: Int?,
    val wind_speed: Float?,
    val wind_deg: Int?,
    val wind_gust: Float?,
    val weather: List<WeatherDTO>?,
    val pop: Float?
)

fun List<HourlyDTO>?.toHourWeatherList() : List<HourWeather>{
    val result = this?.map{
        HourWeather(
            time = it.dt.toString(),
            temperature = it.temp.toString(),
            feelsLike = it.feels_like.toString(),
            windSpeed = it.wind_speed.toString(),
            windGust = it.wind_gust.toString(),
            clouds = it.clouds.toString(),
            humidity = it.humidity.toString(),
            pressure = it.pressure.toString(),
            dewPoint = it.dew_point.toString(),
            windDeg = it.wind_deg.toString(),
            weather = it.weather?.map { w ->
                Weather(
                    id = w.id ?: 0,
                    main = w.main.toString(),
                    description = w.description.toString(),
                    icon = w.icon.toString()
                )
            } ?: emptyList()
        )
    } ?: emptyList()
    return result
}