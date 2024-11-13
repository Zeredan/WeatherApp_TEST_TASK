package com.example.todayforecastfeature.UseCases.GetCurrentAndHourlyWeatherUseCase

import com.example.weathermodeldata.DataClasses.MainClasses.CurrentWeather
import com.example.weathermodeldata.DataClasses.MainClasses.HourWeather

class CurrentAndHourlyWeatherResult(
    val noStub : Boolean,
    val current: CurrentWeather,
    val hourly: List<HourWeather>
)