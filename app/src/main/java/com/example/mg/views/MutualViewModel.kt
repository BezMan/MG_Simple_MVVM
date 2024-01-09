package com.example.mg.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mg.data.MyTask
import com.example.mg.data.NoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MutualViewModel : ViewModel() {

    private val repository = NoteRepository()

    internal var currentTask: MyTask? = null

    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO

    val taskListState: LiveData<List<MyTask>>
        get() = repository.getAllNotes()


    suspend fun insert(task: MyTask): Long {
        val res = viewModelScope.async(coroutineDispatcher) {
            repository.upsert(task)
        }
        return res.await()
    }

    suspend fun update(task: MyTask) {
        viewModelScope.launch(coroutineDispatcher) {
            repository.upsert(task)
        }
    }

    fun delete(task: MyTask) {
        viewModelScope.launch(coroutineDispatcher) {
            repository.delete(task)
        }
    }

//    fun getNoteById(noteId: Long): LiveData<MyTask> = repository.getNoteById(noteId)

}