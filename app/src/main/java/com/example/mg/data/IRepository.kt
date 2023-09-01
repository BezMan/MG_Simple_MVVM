package com.example.mg.data

import androidx.lifecycle.LiveData

interface IRepository {
    fun insert(myTask: MyTask): Long
    fun update(myTask: MyTask)
    fun delete(myTask: MyTask)
    fun clearAllData()
    fun getNoteById(noteId: Long): LiveData<MyTask>
    fun getAllNotes(): LiveData<List<MyTask>>

}
