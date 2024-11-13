package com.example.savedoptionsmodeldata.APIs

import com.example.savedoptionsmodeldata.APIs.Room.Entities.SavedOptionsEntity
import com.example.savedoptionsmodeldata.APIs.Room.Entities.toSavedOptions
import com.example.savedoptionsmodeldata.APIs.Room.SavedOptionsRoomDAO
import com.example.savedoptionsmodeldata.DataClasses.SavedOptions
import com.example.savedoptionsmodeldata.DataClasses.toSavedOptionsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SavedOptionsApiRoomImplementation(
    val dao: SavedOptionsRoomDAO
) : SavedOptionsApi
{
    override suspend fun getSavedOptions(): List<SavedOptions> {
        return dao.selectAllOptions().also{ println("ROOM_DATA: $it") }.map{
            it.toSavedOptions()
        }
    }

    override suspend fun saveOptions(options: SavedOptions) {
        dao.clearOptions()
        dao.insertOptions(options.toSavedOptionsEntity(1))
    }
}