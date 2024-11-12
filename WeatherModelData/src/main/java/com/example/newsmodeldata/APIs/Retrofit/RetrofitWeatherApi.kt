package com.example.newsmodeldata.APIs.Retrofit

import com.example.newsmodeldata.APIs.Retrofit.DTO.WeatherResponseDTO
import com.example.newsmodeldata.DataClasses.MainClasses.CurrentWeather
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface RetrofitWeatherApi {
    @GET("/data/3.0/onecall")
    suspend fun getCurrentData(@QueryMap args: Map<String, String>) : WeatherResponseDTO
}