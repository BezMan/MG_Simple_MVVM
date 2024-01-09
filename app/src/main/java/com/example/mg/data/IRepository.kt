package com.example.mg.data

import androidx.lifecycle.LiveData

interface IRepository {
    suspend fun upsert(myTask: MyTask): Long
    suspend fun delete(myTask: MyTask)
    suspend fun clearAllData()
    fun getNoteById(noteId: Long): LiveData<MyTask>
    fun getAllNotes(): LiveData<List<MyTask>>

}
