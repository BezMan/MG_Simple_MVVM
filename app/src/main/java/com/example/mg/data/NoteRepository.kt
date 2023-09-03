package com.example.mg.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.mg.App
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteRepository : IRepository {

    private val noteDao: NoteDao = App.database.noteDao()

    override suspend fun insert(myTask: MyTask): Long {
        return noteDao.insert(myTask)
    }

    override suspend fun update(myTask: MyTask) {
        noteDao.update(myTask)
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

