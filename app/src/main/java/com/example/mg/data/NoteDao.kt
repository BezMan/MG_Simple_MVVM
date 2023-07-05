package com.example.mg.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(myTask: MyTask): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(myTask: List<MyTask>): List<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(myTask: MyTask)

    @Delete
    fun delete(myTask: MyTask)

    @Query("DELETE FROM note_table")
    fun deleteAllNotes()

    @Query("SELECT * FROM note_table ORDER BY timestamp DESC")
    fun getAllNotesByPriority(): LiveData<List<MyTask>>

    @Query("SELECT * FROM note_table WHERE id = :noteId")
    fun getNoteById(noteId: Long): LiveData<MyTask>
}

