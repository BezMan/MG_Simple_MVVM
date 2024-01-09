package com.example.mg.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.mg.App

class NoteRepository : IRepository {

    private val noteDao: NoteDao = App.database.noteDao()

    override suspend fun upsert(myTask: MyTask): Long {
        return noteDao.insert(myTask)
    }

    override suspend fun delete(myTask: MyTask) {
        noteDao.delete(myTask)
    }

    override suspend fun clearAllData() {
        App.database.clearAllTables()
    }

    override fun getNoteById(noteId: Long): LiveData<MyTask> {
        return noteDao.getNoteById(noteId).asLiveData()
    }

    override fun getAllNotes(): LiveData<List<MyTask>> {
        return noteDao.getAllNotesByPriority().asLiveData()
    }
}

