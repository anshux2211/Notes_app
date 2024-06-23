package com.example.notes.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="Note_Table")
data class Note_Data(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val Note_Name: String,
    val Note_Content: String
)