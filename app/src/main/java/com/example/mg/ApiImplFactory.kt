package com.example.mg

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
    fun getArticles(): MutableList<MyTask>?
}


private class ArticlesApi : ApiImpl {

    override fun getArticles(): MutableList<MyTask> {
        return mutableListOf(MyTask(111, "hello", ))
    }
}

//private class FakeArticlesApi : ApiImpl {
//
//    override fun getArticles(): Articles? {
//        // Get the JSON data
//        val jsonString = Consts.jsonRaw
//
//        val articles = Gson().fromJson(jsonString, Articles::class.java)
//
//        return articles
//    }
//
//
//}

