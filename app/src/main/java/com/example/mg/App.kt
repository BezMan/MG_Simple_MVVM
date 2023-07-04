package com.example.mg

import android.app.Application

class App : Application() {

    companion object {
        var myTasks: MutableList<MyTask> = ApiImplFactory.tasksImpl.getArticles()!!
    }
}
