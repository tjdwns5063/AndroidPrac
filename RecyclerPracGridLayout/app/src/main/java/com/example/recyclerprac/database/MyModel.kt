package com.example.recyclerprac.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_list")
data class MyModel(
    @ColumnInfo(name = "name")
    var name: String) {
    @PrimaryKey(autoGenerate = true)
    var nameId: Int = 0
}