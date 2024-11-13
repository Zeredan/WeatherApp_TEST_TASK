package com.example.weathermodeldata.DataClasses.MainClasses

import com.example.weathermodeldata.DataClasses.SupportingClasses.Weather

data class CurrentWeather(
    val time: String,
    val sunRise: String,
    val sunSet: String,
    val temperature: String,
    val feelsLike: String,
    val humidity: String,
    val pressure: String,
    val dewPoint: String,
    val windSpeed: String,
    val windGust: String,
    val windDeg: String,
    val clouds: String,
    val weather: List<Weather>
)