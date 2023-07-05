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
    fun getArticles(): List<MyTask>
}


private class ArticlesApi : ApiImpl {

    override fun getArticles(): List<MyTask> {
        return NoteRepository().getAllNotes().value ?: listOf()
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

