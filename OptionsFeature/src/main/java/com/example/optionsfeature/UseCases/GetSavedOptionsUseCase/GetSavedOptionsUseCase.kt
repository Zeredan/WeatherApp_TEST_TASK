package com.example.optionsfeature.UseCases.GetSavedOptionsUseCase

import com.example.savedoptionsmodeldata.DataClasses.SavedOptions
import com.example.savedoptionsmodeldata.Repositories.SavedOptionsRepository

class GetSavedOptionsUseCase(
    val savedOptionsRepository: SavedOptionsRepository
)
{
    suspend fun getData() : SavedOptions{
        return savedOptionsRepository.getSavedOptions()
    }
}