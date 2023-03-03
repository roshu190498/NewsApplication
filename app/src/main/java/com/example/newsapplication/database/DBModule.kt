package com.example.newsapplication.database

import android.content.Context
import androidx.room.Room
import com.example.newsapplication.database.DBConstants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Singleton
    @Provides
    fun providesNewsDatabbase(@ApplicationContext context : Context) =
        Room.databaseBuilder(
            context,
            NewsDatabbase::class.java,
            DB_NAME
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun providesNewsDao(newsDatabbase: NewsDatabbase) : NewsDao =
        newsDatabbase.newsDao()
}