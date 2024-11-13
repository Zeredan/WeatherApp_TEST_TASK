package com.example.savedoptionsmodeldata.CommonDI

import android.content.Context
import com.example.savedoptionsmodeldata.Repositories.SavedOptionsRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named

@Component(modules = [RoomModule::class, SavedOptionsApiModule::class, SavedOptionsDataSourceModule::class])
interface SavedOptionsDataDIComponent {
    fun resolveSavedOptionsRepository() : SavedOptionsRepository

    @Component.Builder
    interface Builder{
        fun build() : SavedOptionsDataDIComponent

        @BindsInstance
        fun addAppContext(@Named("appContext") appContext: Context) : Builder

        @BindsInstance
        fun addDbName(@Named("dbName") name: String) : Builder
    }
}