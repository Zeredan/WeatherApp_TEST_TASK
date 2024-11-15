package com.example.weathermodeldata.DataClasses.MainClasses

import com.example.weathermodeldata.DataClasses.SupportingClasses.FeelsLike
import com.example.weathermodeldata.DataClasses.SupportingClasses.Temperature
import com.example.weathermodeldata.DataClasses.SupportingClasses.Weather

data class DayWeather(
    val time: String,
    val sunRise: String,
    val sunSet: String,
    val moonRise: String,
    val moonSet: String,
    val moonPhase: String,
    val summary: String,
    val temperature: Temperature,
    val feelsLike: FeelsLike,
    val humidity: String,
    val pressure: String,
    val dewPoint: String,
    val windSpeed: String,
    val windGust: String,
    val windDeg: String,
    val weather: List<Weather>,
    val clouds: String,
    val rain: String,

    )
