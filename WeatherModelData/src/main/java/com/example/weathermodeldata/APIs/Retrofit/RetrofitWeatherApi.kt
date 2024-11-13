package com.example.weathermodeldata.APIs.Retrofit

import com.example.weathermodeldata.APIs.Retrofit.DTO.WeatherResponseDTO
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface RetrofitWeatherApi {
    @GET("/data/3.0/onecall")
    suspend fun getCurrentData(@QueryMap args: Map<String, String>) : WeatherResponseDTO
}