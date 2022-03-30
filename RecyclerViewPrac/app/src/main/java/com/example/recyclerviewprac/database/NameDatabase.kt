package com.example.recyclerviewprac.database

import android.content.Context
import android.content.Entity
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NameEntity::class], version = 1, exportSchema = false)
abstract class NameDatabase : RoomDatabase() {
    abstract val nameDatabseDAO: NameDatabaseDAO

    companion object {
        @Volatile
        private var INSTANCE: NameDatabase? = null
        @Synchronized
        fun getInstance(context: Context): NameDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NameDatabase::class.java, "name_list"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}