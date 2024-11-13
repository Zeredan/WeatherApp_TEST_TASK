package com.example.weathermodeldata.CommonDI

import com.example.weathermodeldata.APIs.Retrofit.RetrofitWeatherApi
import com.example.weathermodeldata.APIs.WeatherApi
import com.example.weathermodeldata.APIs.WeatherApiRetrofitImplementation
import dagger.Module
import dagger.Provides

@Module
class WeatherApiModule {
    @Provides
    fun resolveWeatherApi(retrofitWeatherApi: RetrofitWeatherApi) : WeatherApi{
        return WeatherApiRetrofitImplementation(
            retrofitWeatherApi
        )
    }
}