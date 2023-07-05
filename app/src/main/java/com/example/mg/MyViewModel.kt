package com.example.mg

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mg.data.MyTask
import com.example.mg.data.NoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class MyViewModel : ViewModel() {

    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
    private val repository = NoteRepository()
//    private val _viewState = MutableLiveData<MutableList<MyTask>>()
//    val viewState: LiveData<MutableList<MyTask>>
//        get() = _viewState


    internal var currentTask: MyTask? = null

//    fun fetchData() {
//        viewModelScope.launch(dispatcher) {
//            val articles = ApiImplFactory.tasksImpl.getArticles()
//            _viewState.postValue(articles)
//        }
//    }
//
//    fun addItemToList(myTask: MyTask) {
//        _viewState.value?.add(myTask)
//        _viewState.postValue(_viewState.value)
//    }


    fun insert(task: MyTask): Long {
        return repository.insert(task)
    }

    fun update(task: MyTask) {
        repository.update(task)
    }


    fun getNoteById(noteId: Long): LiveData<MyTask> = repository.getNoteById(noteId)

    fun getAllNotes(): LiveData<List<MyTask>> = repository.getAllNotes()



}