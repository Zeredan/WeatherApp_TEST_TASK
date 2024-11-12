package com.example.todayforecastfeature.ViewModels.UseCases.GetCurrentAndHourlyWeatherUseCase

import com.example.newsmodeldata.DataClasses.MainClasses.CurrentWeather
import com.example.newsmodeldata.DataClasses.MainClasses.HourWeather

class CurrentAndHourlyWeatherResult(
    val noStub : Boolean,
    val current: CurrentWeather,
    val hourly: List<HourWeather>
)