package com.example.notes.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface Note_DAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note_Data)

    @Update
    fun update(note: Note_Data)

    @Delete
    fun delete(note: Note_Data)

    @Query("Select * from Note_Table")
    fun ReadALlData(): LiveData<List<Note_Data>>
}