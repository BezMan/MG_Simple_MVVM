package com.example.mg.data

import androidx.lifecycle.LiveData

interface IRepository {
    suspend fun insert(myTask: MyTask): Long
    suspend fun update(myTask: MyTask)
    suspend fun delete(myTask: MyTask)
    suspend fun clearAllData()
    fun getNoteById(noteId: Long): LiveData<MyTask>
    fun getAllNotes(): LiveData<List<MyTask>>

}
