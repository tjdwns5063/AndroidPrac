package com.example.recyclerviewprac.database

import androidx.room.*

@Dao
interface NameDatabaseDAO {
    @Insert
     fun insert(nameList: NameEntity)
    @Update
     fun update(nameList: NameEntity)
    @Delete
     fun delete(nameList: NameEntity)
    @Query("SELECT * FROM name_list")
     fun getAll(): List<NameEntity>
    @Query("SELECT * FROM name_list ORDER BY nameId DESC LIMIT 1")
    fun getName(): NameEntity?
}