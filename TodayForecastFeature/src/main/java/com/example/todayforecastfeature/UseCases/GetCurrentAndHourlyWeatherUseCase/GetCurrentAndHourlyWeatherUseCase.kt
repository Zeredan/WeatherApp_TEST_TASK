package com.example.todayforecastfeature.UseCases.GetCurrentAndHourlyWeatherUseCase

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
import java.security.Permission

class GetCurrentAndHourlyWeatherUseCase(
    private val remoteWeatherRepository: WeatherRepository,
    private val localStubWeatherRepository: WeatherRepository,
    private val savedOptionsRepository: SavedOptionsRepository,
    private val context: Context
)
{
    //@SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    suspend fun getData() : CurrentAndHourlyWeatherResult {
        val options = savedOptionsRepository.getSavedOptions()
        var (lat, lon) = resolveLatLon(options.useCurrentLocation, options.selectedCity, context)
        return try{ // also there can be async code, just launch 2 coroutines(deferreds) with .async and await them
            CurrentAndHourlyWeatherResult(
                true,
                remoteWeatherRepository.getCurrentWeather(33.44f, -94.04f, options.apiKey),
                remoteWeatherRepository.getHourWeather48(33.44f, -94.04f, options.apiKey)
            )
        }
        catch (e: Exception){
            CurrentAndHourlyWeatherResult(
                false,
                localStubWeatherRepository.getCurrentWeather(33.44f, -94.04f, options.apiKey),
                localStubWeatherRepository.getHourWeather48(33.44f, -94.04f, options.apiKey)
            )
        }
    }
}