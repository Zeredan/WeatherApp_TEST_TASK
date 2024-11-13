package com.example.weekforecastfeature.UseCases.GetDailyWeatherUseCase

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.example.core.DoMainLayer.resolveLatLon
import com.example.savedoptionsmodeldata.Repositories.SavedOptionsRepository
import com.example.weathermodeldata.Repositories.WeatherRepository

class GetDailyWeatherUseCase(
    private val remoteWeatherRepository: WeatherRepository,
    private val localStubWeatherRepository: WeatherRepository,
    private val savedOptionsRepository: SavedOptionsRepository,
    private val context: Context
)
{
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    suspend fun getData() : DailyWeatherResult {
        val options = savedOptionsRepository.getSavedOptions()
        var (lat, lon) = resolveLatLon(options.useCurrentLocation, options.selectedCity, context)
        return try{ // also there can be async code, just launch 2 coroutines(deferreds) with .async and await them
            DailyWeatherResult(
                true,
                remoteWeatherRepository.getDayWeather8(lat, lon, options.apiKey),
            )
        }
        catch (e: Exception){
            DailyWeatherResult(
                false,
                localStubWeatherRepository.getDayWeather8(lat, lon, options.apiKey),
            )
        }
    }
}