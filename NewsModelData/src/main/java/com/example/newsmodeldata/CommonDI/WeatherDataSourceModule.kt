package com.example.newsmodeldata.CommonDI

import com.example.newsmodeldata.APIs.WeatherApi
import com.example.newsmodeldata.DataSources.RemoteWeatherDataSource
import com.example.newsmodeldata.DataSources.WeatherDataSource
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