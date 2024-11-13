package com.example.savedoptionsmodeldata.APIs.Room.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.savedoptionsmodeldata.DataClasses.SavedOptions

@Entity
class SavedOptionsEntity(
    @PrimaryKey
    val optionsId : Int,
    val showAdditionalInfo: Boolean,
    val useCurrentLocation: Boolean,
    val selectedCity: String,
    val apiKey: String
)

fun SavedOptionsEntity.toSavedOptions() : SavedOptions{
    return SavedOptions(
        showAdditionalInfo = this.showAdditionalInfo,
        useCurrentLocation = this.useCurrentLocation,
        selectedCity = this.selectedCity,
        apiKey = this.apiKey
    )
}