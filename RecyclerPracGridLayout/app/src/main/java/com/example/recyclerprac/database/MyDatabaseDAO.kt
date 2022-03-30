package com.example.recyclerprac.database

import androidx.room.*

@Dao
interface MyDatabaseDAO {
    @Insert
    suspend fun insert(myModel: MyModel)
    @Update
    suspend fun update(myModel: MyModel)
    @Delete
    suspend fun delete(myModel: MyModel)
    @Query("DELETE FROM table_list")
    suspend fun clear()
    @Query("SELECT * FROM table_list ORDER BY nameId DESC")
    suspend fun getAll(): List<MyModel>
    @Query("SELECT * FROM table_list ORDER BY nameId DESC LIMIT 1")
    suspend fun getName(): MyModel
}