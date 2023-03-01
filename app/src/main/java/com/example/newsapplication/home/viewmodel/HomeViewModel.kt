package com.example.newsapplication.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.common.ResponseData
import com.example.newsapplication.common.setError
import com.example.newsapplication.common.setLoading
import com.example.newsapplication.common.setSuccess
import com.example.newsapplication.home.model.TopHeadLinesModel
import com.example.newsapplication.home.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel  @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

    val topHeadlines = MutableLiveData<ResponseData<TopHeadLinesModel>>()
    fun getTopHeadLine() {
        topHeadlines.setLoading(null)
        viewModelScope.launch(Dispatchers.IO) {
            homeRepository.getTopHeadLines(
                {success-> topHeadlines.setSuccess(success)},
                {error-> topHeadlines.setError(error)})
        }
    }

}