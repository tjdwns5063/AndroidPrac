package com.example.recyclerre.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MyModel::class], version = 1, exportSchema = false)
abstract class MyDatabase: RoomDatabase() {
    abstract val myDatabaseDAO: MyDatabaseDAO

    companion object {
        @Volatile
        var INSTANCE: MyDatabase? = null
        @Synchronized
        fun getInstance(context: Context): MyDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context,
                    MyDatabase::class.java, "name_table").build()
                    INSTANCE = instance
                }
                return instance
            }

        }
    }
}