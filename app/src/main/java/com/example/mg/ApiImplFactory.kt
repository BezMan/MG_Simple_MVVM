package com.example.mg

object ApiImplFactory {

    internal val tasksImpl by lazy {
        create()
    }

    private fun create(): ApiImpl {
        return FakeArticlesApi()
//        return ArticlesApi()
    }

}

internal interface ApiImpl {
    fun getArticles(): MutableList<MyTask>
}


private class ArticlesApi : ApiImpl {

    override fun getArticles(): MutableList<MyTask> {
        return mutableListOf()
    }
}

private class FakeArticlesApi : ApiImpl {

    override fun getArticles(): MutableList<MyTask> {
        return mutableListOf(
            MyTask(111, "hello",false),
            MyTask(222, "yoyo", true)
        )
    }


}

