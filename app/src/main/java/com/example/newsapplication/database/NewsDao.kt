package com.example.newsapplication.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.newsapplication.database.DBConstants.TABLE_NEWS
import com.example.newsapplication.home.model.Articles

@Dao
interface NewsDao {

    @Insert
    fun insertNews(acticle : Articles)

    @Query("SELECT * FROM $TABLE_NEWS")
    fun getLocalNews() : List<Articles>

    @Transaction
    @Query("DELETE FROM $TABLE_NEWS WHERE title = :tittle")
    fun deleteNews(tittle :String) : Int
}