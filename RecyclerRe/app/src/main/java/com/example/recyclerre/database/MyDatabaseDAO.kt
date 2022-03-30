package com.example.recyclerre.database

import androidx.room.*

@Dao
interface MyDatabaseDAO {
    @Insert
    suspend fun insert(myModel: MyModel)
    @Update
    suspend fun update(myModel: MyModel)
    @Delete
    suspend fun delete(myModel: MyModel)
    @Query("SELECT * FROM name_table ORDER BY nameId DESC")
    suspend fun getAll(): List<MyModel>
    @Query("DELETE FROM name_table")
    suspend fun clear()
}