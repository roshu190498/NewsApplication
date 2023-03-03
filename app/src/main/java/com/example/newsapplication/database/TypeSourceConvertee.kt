package com.example.newsapplication.database

import androidx.room.TypeConverters
import com.example.newsapplication.home.model.Source

class TypeSourceConverter {

    @TypeConverters
    fun SourceToString(value : Source) : String?{
        return "${value.id},${value.name}"
    }

    @TypeConverters
    fun StringToSouce(value: String) : Source{
        return Source(
            value.substringBefore(","),
            value.substringAfter(",")
        )
    }
}