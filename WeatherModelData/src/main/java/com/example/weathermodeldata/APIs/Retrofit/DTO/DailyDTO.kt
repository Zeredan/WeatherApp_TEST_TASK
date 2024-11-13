package com.example.weathermodeldata.APIs.Retrofit.DTO

import com.example.weathermodeldata.DataClasses.MainClasses.DayWeather
import com.example.weathermodeldata.DataClasses.SupportingClasses.FeelsLike
import com.example.weathermodeldata.DataClasses.SupportingClasses.Temperature
import com.example.weathermodeldata.DataClasses.SupportingClasses.Weather

data class DailyDTO(
    val dt: Int?,
    val sunrise: Int?,
    val sunset: Int?,
    val moonrise: Int?,
    val moonset: Int?,
    val moon_phase: Float?,
    val summary: String?,
    val temp: TemperatureDTO?,
    val feels_like: FeelsLikeDTO?,
    val pressure: Int?,
    val humidity: Int?,
    val dew_point: Float?,
    val wind_speed: Float?,
    val wind_deg: Int?,
    val wind_gust: Float?,
    val weather: List<WeatherDTO>?,
    val clouds: Int?,
    val pop: Float?,
    val rain: Float?,
    val uvi: Float?
)

fun List<DailyDTO>?.toDayWeatherList() : List<DayWeather>{
    val result = this?.map{
        DayWeather(
            time = it.dt.toString(),
            moonPhase = it.moon_phase.toString(),
            moonRise = it.moonrise.toString(),
            moonSet = it.moonset.toString(),
            sunRise = it.sunrise.toString(),
            sunSet = it.sunset.toString(),
            rain = it.rain.toString(),
            summary = it.summary.toString(),
            temperature = Temperature(
                morning = it.temp?.morn.toString(),
                day = it.temp?.day.toString(),
                evening = it.temp?.eve.toString(),
                night = it.temp?.night.toString(),
                min = it.temp?.min.toString(),
                max = it.temp?.max.toString()
            ),
            feelsLike = FeelsLike(
                morning = it.feels_like?.morn.toString(),
                day = it.feels_like?.day.toString(),
                evening = it.feels_like?.eve.toString(),
                night = it.feels_like?.night.toString(),
            ),
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
