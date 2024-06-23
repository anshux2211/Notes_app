package com.example.notes

import androidx.lifecycle.ViewModel
import com.example.notes.db.Note_Data
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NotesViewModel(private val repo: Repo):ViewModel() {

    fun getAllNotes()=repo.getAllNote()

    fun insert(Note:Note_Data)
    {
        viewModelScope.launch(Dispatchers.IO){
            repo.insert(Note)
        }

    }
    fun delete(Note:Note_Data)
    {
        viewModelScope.launch(Dispatchers.IO){
            repo.delete(Note)
        }
    }
    fun update(Note:Note_Data)
    {
        viewModelScope.launch(Dispatchers.IO){
            repo.update(Note)
        }
    }
}