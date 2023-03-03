package com.example.newsapplication.home.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapplication.database.DBConstants
import com.google.gson.annotations.SerializedName

data class TopHeadLinesModel (
    @SerializedName("status"       )
    var status : String?             = null,
    @SerializedName("totalResults" )
    var totalResults : Int?                = null,
    @SerializedName("articles"     )
    var articles     : ArrayList<Articles> = arrayListOf()
)


@Entity(
    tableName = DBConstants.TABLE_NEWS,
)
data class Articles (
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
//    @TypeConverters(TypeSourceConverter::class)
//    @SerializedName("source"      ) var source      : Source? = null,
    @SerializedName("author"      ) var author      : String? = null,
    @SerializedName("title"       ) var title       : String? = null,
    @SerializedName("description" ) var description : String? = null,
    @SerializedName("url"         ) var url         : String? = null,
    @SerializedName("urlToImage"  ) var urlToImage  : String? = null,
    @SerializedName("publishedAt" ) var publishedAt : String? = null,
    @SerializedName("content"     ) var content     : String? = null
)

data class Source (
    @SerializedName("id"   ) var id   : String? = null,
    @SerializedName("name" ) var name : String? = null
)
