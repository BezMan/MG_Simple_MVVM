package com.example.mg.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mg.data.MyTask
import com.example.mg.data.NoteRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MutualViewModel : ViewModel() {

    private val repository = NoteRepository()

    internal var currentTask: MyTask? = null

    val taskListState: LiveData<List<MyTask>>
        get() = repository.getAllNotes()


    suspend fun insert(task: MyTask): Long {
//        return repository.insert(task)

        val res = viewModelScope.async {
            repository.insert(task)
        }
        return res.await()
    }

    suspend fun update(task: MyTask) {
        viewModelScope.launch {
            repository.update(task)
        }
    }

    fun delete(task: MyTask) {
        viewModelScope.launch {
            repository.delete(task)
        }
    }

//    fun getNoteById(noteId: Long): LiveData<MyTask> = repository.getNoteById(noteId)

}