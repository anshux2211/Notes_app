package com.example.notes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Note_Data::class],
    version = 1,
    exportSchema = false
)
abstract class Note_Database: RoomDatabase() {

    abstract fun getNoteDao():Note_DAO

    companion object{
        private const val DB_Name="note_database.db"

        private var instance: Note_Database?=null

        operator fun invoke(context: Context)= instance ?: synchronized(Any()){
            instance ?: buildDatabase(context).also{
                instance =it
            }
        }

        private fun buildDatabase(context: Context)= Room.databaseBuilder(
            context.applicationContext,
            Note_Database::class.java,
            DB_Name
        ).build()
    }


}