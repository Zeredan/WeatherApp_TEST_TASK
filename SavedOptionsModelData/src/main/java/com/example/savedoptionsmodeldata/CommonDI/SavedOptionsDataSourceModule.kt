package com.example.savedoptionsmodeldata.CommonDI

import com.example.savedoptionsmodeldata.APIs.SavedOptionsApi
import com.example.savedoptionsmodeldata.DataSources.LocalSavedOptionsDataSource
import com.example.savedoptionsmodeldata.DataSources.SavedOptionsDataSource
import dagger.Module
import dagger.Provides

@Module
class SavedOptionsDataSourceModule {
    @Provides
    fun provideSavedOptionsDataSource(api: SavedOptionsApi) : SavedOptionsDataSource{
        return LocalSavedOptionsDataSource(api)
    }
}