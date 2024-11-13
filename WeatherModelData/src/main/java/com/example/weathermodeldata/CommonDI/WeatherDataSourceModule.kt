package com.example.weathermodeldata.CommonDI

import com.example.weathermodeldata.APIs.WeatherApi
import com.example.weathermodeldata.DataSources.RemoteWeatherDataSource
import com.example.weathermodeldata.DataSources.WeatherDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

@Module
class WeatherDataSourceModule {
    @Provides
    fun resolveWeatherDataSource(
        api: WeatherApi,
        @Named("coroutineContext") context: CoroutineContext
    ) : WeatherDataSource{
        return RemoteWeatherDataSource(api, context)
    }
}