package com.example.newsapplication.home.repository

import com.example.newsapplication.common.enqueue
import com.example.newsapplication.home.api.HomeApis
import com.example.newsapplication.home.model.TopHeadLinesModel
import javax.inject.Inject

class HomeRepository @Inject constructor(private val homeApis: HomeApis) {

    fun getTopHeadLines(
        success: (topHeadLinesModel: TopHeadLinesModel) -> Unit,
        fail: (error: String) -> Unit ){
        homeApis.getTopHeadLines().enqueue {
            onResponse={
                it.body()?.let {
                    success.invoke(it)
                }?: kotlin.run {
                    fail.invoke("Something went wrong")
                }
            }
            onFailure={
                fail.invoke("Something went Wrong")
            }
        }
    }}