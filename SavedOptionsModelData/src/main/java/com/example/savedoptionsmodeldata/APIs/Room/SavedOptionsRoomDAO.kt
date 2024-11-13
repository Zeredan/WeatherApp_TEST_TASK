package com.example.savedoptionsmodeldata.APIs.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.savedoptionsmodeldata.APIs.Room.Entities.SavedOptionsEntity
import com.example.savedoptionsmodeldata.DataClasses.SavedOptions
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedOptionsRoomDAO {
    @Query("SELECT * FROM SavedOptionsEntity")
    suspend fun selectAllOptions() : List<SavedOptionsEntity>

    @Query("DELETE FROM SavedOptionsEntity")
    suspend fun clearOptions() : Int

    @Insert
    suspend fun insertOptions(options: SavedOptionsEntity)
}