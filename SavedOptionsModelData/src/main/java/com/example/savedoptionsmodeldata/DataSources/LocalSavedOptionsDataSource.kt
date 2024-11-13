package com.example.savedoptionsmodeldata.DataSources

import com.example.savedoptionsmodeldata.APIs.SavedOptionsApi
import com.example.savedoptionsmodeldata.DataClasses.SavedOptions
import kotlinx.coroutines.flow.Flow

class LocalSavedOptionsDataSource(
    val api: SavedOptionsApi
) : SavedOptionsDataSource
{
    override suspend fun getSavedOptions() : List<SavedOptions>{
        return api.getSavedOptions()
    }

    override suspend fun saveOptions(options: SavedOptions) {
        api.saveOptions(options)
    }
}