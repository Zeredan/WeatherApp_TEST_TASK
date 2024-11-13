package com.example.optionsfeature.ViewModels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.optionsfeature.UseCases.GetSavedOptionsUseCase.GetSavedOptionsUseCase
import com.example.optionsfeature.UseCases.SaveOptionsUseCase.SaveOptionsUseCase
import com.example.savedoptionsmodeldata.CommonDI.DaggerSavedOptionsDataDIComponent
import com.example.savedoptionsmodeldata.DataClasses.SavedOptions
import kotlinx.coroutines.launch

//no memory leaks due to recompose updates
class OptionsViewModel(var context: Context) : ViewModel() {
    var savedOptions by mutableStateOf<SavedOptions>(SavedOptions(true, true, ""))
    var isSomethingChanged by mutableStateOf(false)
        private set


    val savedOptionsDaggerComponent = DaggerSavedOptionsDataDIComponent
        .builder()
        .addAppContext(context.applicationContext)
        .addDbName("savedOptionsDatabase")
        .build()

    init{
        val getSavedOptionsUseCase = GetSavedOptionsUseCase(
            savedOptionsDaggerComponent.resolveSavedOptionsRepository()
        )
        viewModelScope.launch {
            savedOptions = getSavedOptionsUseCase.getData()
        }
    }

    fun updateShowAdditionalInfo(value: Boolean){
        savedOptions = savedOptions.copy(showAdditionalInfo = value)
        isSomethingChanged = true
    }
    fun updateUseCurrentLocation(value: Boolean){
        savedOptions = savedOptions.copy(useCurrentLocation = value)
        isSomethingChanged = true
    }
    fun updateSelectedCity(value: String){
        savedOptions = savedOptions.copy(selectedCity = value)
        isSomethingChanged = true
    }
    fun updateApiKey(value: String){
        savedOptions = savedOptions.copy(apiKey = value)
        isSomethingChanged = true
    }
    fun saveOptions() {
        val saveOptionsUseCase = SaveOptionsUseCase(
            savedOptionsDaggerComponent.resolveSavedOptionsRepository()
        )
        viewModelScope.launch {
            saveOptionsUseCase.saveData(savedOptions)
            isSomethingChanged = false
        }
    }
}