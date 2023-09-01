package com.example.mg.data

import androidx.lifecycle.LiveData
import com.example.mg.App
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteRepository: IRepository {

    private val noteDao: NoteDao = App.database.noteDao()
    private val repoScope = CoroutineScope(Dispatchers.IO)

    override fun insert(myTask: MyTask): Long {
        return noteDao.insert(myTask)
    }

    override fun update(myTask: MyTask) {
        repoScope.launch { noteDao.update(myTask) }
    }

    override fun delete(myTask: MyTask){
        noteDao.delete(myTask)
    }

    override fun clearAllData() {
        App.database.clearAllTables()
    }

    override fun getNoteById(noteId: Long): LiveData<MyTask> {
        return noteDao.getNoteById(noteId)
    }

    override fun getAllNotes(): LiveData<List<MyTask>> {
        return noteDao.getAllNotesByPriority()
    }
}

