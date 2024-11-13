package com.example.core.DoMainLayer

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
suspend fun resolveLatLon(useCurrentLocation: Boolean, cityName: String, context: Context) : Pair<Float, Float>{
    var (lat, lon) = 0f to 0f

    try{
        if (!useCurrentLocation) throw Exception("toSelected")
        val lm = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (
            (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            &&
            (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        ) throw Exception("notGranted")
        val location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        lat = location!!.latitude.toFloat()
        lon = location.longitude.toFloat()
        println("USING_LOCATION_MANAGER_C: ${lat}|${lon}")
    }
    catch(e: Exception){
        try {
            val geocoder = Geocoder(context, Locale.getDefault())
            geocoder.getFromLocationName(cityName, 1)?.get(0)?.run{ // yes yes deprecated method. But it is better for my blocking case(this is called in suspend func
                lat = latitude.toFloat()
                lon = longitude.toFloat()
            }
            println("USING_SELECTED_CITY_C: ${lat}|${lon}")
        }
        catch (e1: Exception){
            println("city name is incorrect")
        }
    }
    return lat to lon
}