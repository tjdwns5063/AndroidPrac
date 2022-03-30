package com.example.recyclerre.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "name_table")
data class MyModel(
    @ColumnInfo(name = "name")
    var name: String) {
    @PrimaryKey(autoGenerate = true)
    var nameId: Int = 0
}
