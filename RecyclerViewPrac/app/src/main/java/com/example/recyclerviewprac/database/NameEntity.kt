package com.example.recyclerviewprac.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "name_list")
data class NameEntity(
    @ColumnInfo(name = "name")
    var name: String) {
    @PrimaryKey(autoGenerate = true)
    var nameId: Long = 0L
}
