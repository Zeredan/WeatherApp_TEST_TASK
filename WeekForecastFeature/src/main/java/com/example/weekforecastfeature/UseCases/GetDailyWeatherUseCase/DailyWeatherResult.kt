package com.example.weekforecastfeature.UseCases.GetDailyWeatherUseCase

import com.example.weathermodeldata.DataClasses.MainClasses.DayWeather

class DailyWeatherResult(
    val noStub : Boolean,
    val daily: List<DayWeather>
)