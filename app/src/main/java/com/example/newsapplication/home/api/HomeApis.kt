package com.example.newsapplication.home.api

import com.example.newsapplication.home.model.TopHeadLinesModel
import com.example.newsapplication.network.API_KEY
import com.example.newsapplication.network.COUNTRY_CODE
import com.example.newsapplication.network.TOP_HEADLINES
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApis {
    @GET(TOP_HEADLINES)
    fun getTopHeadLines(
        @Query("country") country: String = COUNTRY_CODE,
        @Query("apiKey") apiKey : String = API_KEY
        ) : Call<TopHeadLinesModel>
}