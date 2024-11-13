package com.example.savedoptionsmodeldata.DataClasses

import com.example.savedoptionsmodeldata.APIs.Room.Entities.SavedOptionsEntity


data class SavedOptions(
    val showAdditionalInfo: Boolean,
    val useCurrentLocation: Boolean,
    val selectedCity: String,
    val apiKey: String = ""
)

fun SavedOptions.toSavedOptionsEntity(id: Int) : SavedOptionsEntity{
    return SavedOptionsEntity(
        optionsId = id,
        showAdditionalInfo, useCurrentLocation, selectedCity, apiKey
    )
}