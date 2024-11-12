package com.example.newsmodeldata.APIs.Retrofit.DTO

import com.example.newsmodeldata.DataClasses.MainClasses.CurrentWeather
import com.example.newsmodeldata.DataClasses.SupportingClasses.Weather

data class CurrentDTO(
    val dt: Int?,
    val sunrise: Int?,
    val sunset: Int?,
    val temp: Float?,
    val feels_like: Float?,
    val pressure: Int?,
    val humidity: Int?,
    val dew_point: Float?,
    val uvi: Float?,
    val clouds: Int?,
    val visibility: Int?,
    val wind_speed: Float?,
    val wind_deg: Int?,
    val wind_gust: Float?,
    val weather: List<WeatherDTO>?
)

fun CurrentDTO?.toCurrentWeather() : CurrentWeather{
    val result = CurrentWeather(
        time = this?.dt.toString(),
        sunRise = this?.sunrise.toString(),
        sunSet = this?.sunset.toString(),
        temperature = this?.temp.toString(),
        feelsLike = this?.feels_like.toString(),
        humidity = this?.humidity.toString(),
        pressure = this?.pressure.toString(),
        dewPoint = this?.dew_point.toString(),
        windSpeed = this?.wind_speed.toString(),
        windDeg = this?.wind_deg.toString(),
        windGust = this?.wind_gust.toString(),
        clouds = this?.clouds.toString(),
        weather = this?.weather?.map{
            Weather(
                id = it.id ?: 0,
                main = it.main.toString(),
                description = it.description.toString(),
                icon = it.icon.toString()
            )
        } ?: emptyList()
    )
    return result
}