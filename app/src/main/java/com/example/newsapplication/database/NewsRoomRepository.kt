package com.example.newsapplication.database

import com.example.newsapplication.home.model.Articles
import javax.inject.Inject

class NewsRoomRepository @Inject constructor(
    private val newsDao: NewsDao
) {
    fun insertArticle(articles: Articles,success: (status: String) -> Unit,fail: (error: String) -> Unit)  {
        try {
            newsDao.insertNews(articles).let {
                success.invoke("SUCCESS")
            }
        }catch (e:java.lang.Exception){
            fail.invoke("FAILURE")
            e.printStackTrace()
        }
    }

    fun getLocalNews(success: (articles: List<Articles>) -> Unit,fail: (error: String) -> Unit){
        try {
            newsDao.getLocalNews().let {
                success.invoke(it)
            }
        }catch (e:Exception){
            fail.invoke("FAILURE")
        }
    }

    fun deleteNews(titttle : String, success: (c: Int) -> Unit,fail: (error: Int) -> Unit){
        try {
            newsDao.deleteNews(titttle).let {
                success.invoke(it)
            }
        }catch (e:Exception){
            fail.invoke(-1)
        }
    }
}