package com.example.mg.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mg.data.MyTask
import com.example.mg.data.NoteRepository

class MutualViewModel : ViewModel() {

    private val repository = NoteRepository()

    internal var currentTask: MyTask? = null

    val taskListState: LiveData<List<MyTask>>
        get() = repository.getAllNotes()


    fun insert(task: MyTask): Long {
        return repository.insert(task)
    }

    fun update(task: MyTask) {
        repository.update(task)
    }

    fun delete(task: MyTask) {
        repository.delete(task)
    }

//    fun getNoteById(noteId: Long): LiveData<MyTask> = repository.getNoteById(noteId)

}