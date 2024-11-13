package com.example.optionsfeature.UseCases.SaveOptionsUseCase

import com.example.savedoptionsmodeldata.DataClasses.SavedOptions
import com.example.savedoptionsmodeldata.Repositories.SavedOptionsRepository

class SaveOptionsUseCase(
    val savedOptionsRepository: SavedOptionsRepository
)
{
    suspend fun saveData(so: SavedOptions){
        try{
            savedOptionsRepository.saveOptions(so)
        }
        catch (e: Exception){

        }
    }
}