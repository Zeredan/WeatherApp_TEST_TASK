package com.example.savedoptionsmodeldata.CommonDI

import com.example.savedoptionsmodeldata.APIs.Room.SavedOptionsRoomDAO
import com.example.savedoptionsmodeldata.APIs.SavedOptionsApi
import com.example.savedoptionsmodeldata.APIs.SavedOptionsApiRoomImplementation
import dagger.Module
import dagger.Provides

@Module
class SavedOptionsApiModule {
    @Provides
    fun provideSavedOptionsApiModule(dao: SavedOptionsRoomDAO) : SavedOptionsApi{
        return SavedOptionsApiRoomImplementation(dao)
    }
}