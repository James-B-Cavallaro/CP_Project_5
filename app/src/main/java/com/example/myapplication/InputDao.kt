package com.example.myapplication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface InputDao{
    @Query("SELECT * FROM entry_table")
    fun getAll(): Flow<List<InputEntry>>

    @Insert
    fun insertEntry(entry: InputEntry)

    @Query("DELETE FROM entry_table")
    fun deleteAll()
}