package com.example.savedoptionsmodeldata.APIs

import com.example.savedoptionsmodeldata.DataClasses.SavedOptions
import kotlinx.coroutines.flow.Flow

interface SavedOptionsApi {
    suspend fun getSavedOptions() : List<SavedOptions>

    suspend fun saveOptions(options: SavedOptions)
}