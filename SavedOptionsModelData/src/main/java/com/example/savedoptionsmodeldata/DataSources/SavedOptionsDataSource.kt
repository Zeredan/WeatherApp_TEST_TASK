package com.example.savedoptionsmodeldata.DataSources

import com.example.savedoptionsmodeldata.DataClasses.SavedOptions
import kotlinx.coroutines.flow.Flow

interface SavedOptionsDataSource {
    suspend fun getSavedOptions() : List<SavedOptions>
    suspend fun saveOptions(options: SavedOptions)
}