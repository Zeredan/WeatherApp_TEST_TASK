package com.example.savedoptionsmodeldata.APIs.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.savedoptionsmodeldata.APIs.Room.Entities.SavedOptionsEntity

@Database(version = 2, entities = [SavedOptionsEntity::class])
abstract class SavedOptionsDatabase : RoomDatabase(){
    abstract fun getSavedOptionsRoomDAO() : SavedOptionsRoomDAO
}