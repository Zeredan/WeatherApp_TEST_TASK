package com.example.newsmodeldata.DataClasses.MainClasses

import com.example.newsmodeldata.DataClasses.SupportingClasses.Weather

data class HourWeather(
    val time: String,
    val temperature: String,
    val feelsLike: String,
    val windSpeed: String,
    val windGust: String,
    val clouds: String,
    val humidity: String,
    val pressure: String,
    val dewPoint: String,
    val windDeg: String,
    val weather: List<Weather>
)
