package com.example.savedoptionsmodeldata.Repositories

import com.example.savedoptionsmodeldata.DataClasses.SavedOptions
import com.example.savedoptionsmodeldata.DataSources.SavedOptionsDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SavedOptionsRepository @Inject constructor(
    val savedOptionsDataSource: SavedOptionsDataSource
)
{
    suspend fun getSavedOptions() : SavedOptions{
        return try{
            savedOptionsDataSource.getSavedOptions()[0]
        }
        catch (e: Exception){
            println("E12: $e")
            SavedOptions(
                showAdditionalInfo = true,
                useCurrentLocation = true,
                selectedCity = ""
            )
        }
    }

    suspend fun saveOptions(options: SavedOptions) {
        savedOptionsDataSource.saveOptions(options)
    }
}