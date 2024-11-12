package com.example.newsmodeldata.CommonDI

import com.example.newsmodeldata.Repositories.WeatherRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

@Component(modules = [WeatherApiModule::class, WeatherDataSourceModule::class, RetrofitModule::class])
interface WeatherModelDataDIComponent {
    fun resolveWeatherRepository() : WeatherRepository

    @Component.Builder
    interface Builder{
        fun build() : WeatherModelDataDIComponent

        @BindsInstance
        fun addBaseUrl(@Named("url") url: String) : Builder

        @BindsInstance
        fun addCoroutineContext(@Named("coroutineContext") context: CoroutineContext) : Builder
    }
}