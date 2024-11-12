package com.example.newsmodeldata.APIs

import android.content.Context
import com.example.newsmodeldata.APIs.Retrofit.DTO.WeatherResponseDTO
import com.example.newsmodeldata.APIs.Retrofit.DTO.toCurrentWeather
import com.example.newsmodeldata.APIs.Retrofit.DTO.toDayWeatherList
import com.example.newsmodeldata.APIs.Retrofit.DTO.toHourWeatherList
import com.example.newsmodeldata.DataClasses.MainClasses.CurrentWeather
import com.example.newsmodeldata.DataClasses.MainClasses.DayWeather
import com.example.newsmodeldata.DataClasses.MainClasses.HourWeather
import com.example.newsmodeldata.R
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