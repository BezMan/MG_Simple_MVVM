package com.example.mg

import com.example.mg.data.MyTask
import com.example.mg.data.NoteRepository

object ApiImplFactory {

    internal val tasksImpl by lazy {
        create()
    }

    private fun create(): ApiImpl {
//        return FakeArticlesApi()
        return ArticlesApi()
    }

}

internal interface ApiImpl {
    fun getArticles(): MutableList<MyTask>
}


private class ArticlesApi : ApiImpl {

    override fun getArticles(): MutableList<MyTask> {
        return NoteRepository().getAllNotes().value ?: mutableListOf()
    }
}

private class FakeArticlesApi : ApiImpl {

    override fun getArticles(): MutableList<MyTask> {
        return mutableListOf(
            MyTask("hello",false),
            MyTask("yoyo", true)
        )
    }


}

