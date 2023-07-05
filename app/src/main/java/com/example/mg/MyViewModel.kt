package com.example.mg

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mg.data.MyTask
import com.example.mg.data.NoteRepository

class MyViewModel : ViewModel() {

    private val repository = NoteRepository()

    internal var currentTask: MyTask? = null


    fun insert(task: MyTask): Long {
        return repository.insert(task)
    }

    fun update(task: MyTask) {
        repository.update(task)
    }


    fun getNoteById(noteId: Long): LiveData<MyTask> = repository.getNoteById(noteId)

    fun getAllNotes(): LiveData<List<MyTask>> = repository.getAllNotes()



}