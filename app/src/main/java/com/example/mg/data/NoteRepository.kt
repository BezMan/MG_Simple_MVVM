package com.example.mg.data

import androidx.lifecycle.LiveData
import com.example.mg.App
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteRepository {

    private val noteDao: NoteDao = App.database.noteDao()
    private val repoScope = CoroutineScope(Dispatchers.IO)

    fun insert(myTask: MyTask): Long {
        return noteDao.insert(myTask)
    }

    fun update(myTask: MyTask) {
        repoScope.launch { noteDao.update(myTask) }
    }

    fun delete(myTask: MyTask){
        noteDao.delete(myTask)
    }

    fun clearAllData() {
        App.database.clearAllTables()
    }

    fun getNoteById(noteId: Long): LiveData<MyTask> {
        return noteDao.getNoteById(noteId)
    }

    fun getAllNotes(): LiveData<List<MyTask>> {
        return noteDao.getAllNotesByPriority()
    }
}

