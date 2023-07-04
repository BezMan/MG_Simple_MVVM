package com.example.mg

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    private val _viewState = MutableLiveData<MutableList<MyTask>>()
    val viewState: LiveData<MutableList<MyTask>>
        get() = _viewState


    fun fetchData() {
        viewModelScope.launch(dispatcher) {
            val articles = ApiImplFactory.tasksImpl.getArticles()
            _viewState.postValue(articles)
        }
    }

    fun addItemToList(myTask: MyTask) {
        _viewState.value?.add(myTask)
        _viewState.postValue(_viewState.value)
    }


}