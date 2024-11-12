package com.example.newsmodeldata.CommonDI

import com.example.newsmodeldata.APIs.Retrofit.RetrofitWeatherApi
import com.example.newsmodeldata.APIs.WeatherApi
import com.example.newsmodeldata.APIs.WeatherApiRetrofitImplementation
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