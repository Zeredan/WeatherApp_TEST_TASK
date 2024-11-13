package com.example.weathermodeldata.APIs

import android.content.Context
import com.example.newsmodeldata.R
import com.example.weathermodeldata.APIs.Retrofit.DTO.WeatherResponseDTO
import com.example.weathermodeldata.APIs.Retrofit.DTO.toCurrentWeather
import com.example.weathermodeldata.APIs.Retrofit.DTO.toDayWeatherList
import com.example.weathermodeldata.APIs.Retrofit.DTO.toHourWeatherList
import com.example.weathermodeldata.DataClasses.MainClasses.CurrentWeather
import com.example.weathermodeldata.DataClasses.MainClasses.DayWeather
import com.example.weathermodeldata.DataClasses.MainClasses.HourWeather
import com.google.gson.Gson
import java.io.InputStreamReader

class WeatherApiStubImplementation(val context: Context) : WeatherApi {
    override suspend fun getCurrentWeather(lat: Float, lon: Float, appId: String): CurrentWeather {
        val str = with(context){
            InputStreamReader(resources.openRawResource(R.raw.example_json_response)).use{
                it.readLines().joinToString("").also{
                }
            }
        }
        val responseResult = Gson().fromJson(str, WeatherResponseDTO::class.java)
        return responseResult.current.toCurrentWeather()
    }

    override suspend fun getHourWeather48(lat: Float, lon: Float, appId: String): List<HourWeather> {
        val str = with(context){
            InputStreamReader(resources.openRawResource(R.raw.example_json_response)).use{
                it.readLines().joinToString("").also{
                }
            }
        }
        val responseResult = Gson().fromJson(str, WeatherResponseDTO::class.java)
        return responseResult.hourly.toHourWeatherList()
    }

    override suspend fun getDayWeather8(lat: Float, lon: Float, appId: String): List<DayWeather> {
        val str = with(context){
            InputStreamReader(resources.openRawResource(R.raw.example_json_response)).use{
                it.readLines().joinToString("").also{
                }
            }
        }
        val responseResult = Gson().fromJson(str, WeatherResponseDTO::class.java)
        return responseResult.daily.toDayWeatherList()
    }

}