package com.example.newsmodeldata.APIs.Retrofit.DTO

data class WeatherResponseDTO(
    val lat: Float?,
    val lon: Float?,
    val timezone: String?,
    val timezone_offset: Int?,
    val current: CurrentDTO?,
    val hourly: List<HourlyDTO>?,
    val daily: List<DailyDTO>?
)
