package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "entry_table")
data class InputEntry(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "mood") val mood: String?,
    @ColumnInfo(name = "rating") val rating: String?,
    @ColumnInfo(name = "date") val date: String?
)