package com.example.newsapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapplication.home.model.Articles


@Database(
    entities = [Articles::class],
    version = 1,
    exportSchema = false
)
abstract class NewsDatabbase : RoomDatabase(){
    abstract fun newsDao() : NewsDao
}