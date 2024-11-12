package com.example.newsmodeldata.CommonDI

import com.example.newsmodeldata.APIs.Retrofit.RetrofitWeatherApi
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class RetrofitModule {
    @Provides
    fun resolveRetrofitWeatherApi(retrofit: Retrofit) : RetrofitWeatherApi{
        return retrofit.create(RetrofitWeatherApi::class.java)
    }

    @Provides
    fun provideRetrofit(@Named("url") url: String, convertedFactory: Converter.Factory, client: OkHttpClient) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(convertedFactory)
            .client(client)
            .build()
    }

    @Provides
    fun provideGsonFactory() : Converter.Factory{
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideOkHttpClient(interceptors: Set<@JvmSuppressWildcards Interceptor>) : OkHttpClient{
        return OkHttpClient.Builder()
            .apply{
                interceptors.forEach {
                    addInterceptor(it)
                }
            }
            .build()
    }

    @Provides
    @IntoSet
    fun provideLoggingInterceptor() : Interceptor{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}