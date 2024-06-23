package com.example.notes

import androidx.annotation.RestrictTo
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.notes.db.Note_DAO
import com.example.notes.db.Note_Data
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

class Repo(private var dao:Note_DAO) {

    fun insert(Note:Note_Data){
        dao.insert(Note)
    }
    fun delete(Note: Note_Data){
        dao.delete(Note)
    }
    fun update(Note: Note_Data){
        dao.update(Note)
    }
    fun getAllNote()=dao.ReadALlData()
}