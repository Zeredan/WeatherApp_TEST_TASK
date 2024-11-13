package com.example.savedoptionsmodeldata.CommonDI

import android.content.Context
import androidx.room.Room
import com.example.savedoptionsmodeldata.APIs.Room.SavedOptionsDatabase
import com.example.savedoptionsmodeldata.APIs.Room.SavedOptionsRoomDAO
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class RoomModule {
    @Provides
    fun provideSavedOptionsRoomDAO(@Named("appContext") appContext: Context, @Named("dbName") dbName: String) : SavedOptionsRoomDAO{
        return Room.databaseBuilder(appContext, SavedOptionsDatabase::class.java, dbName)
            .fallbackToDestructiveMigration()
            .build()
            .getSavedOptionsRoomDAO()
    }
}