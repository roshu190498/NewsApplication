package com.example.newsapplication.database

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.home.model.Articles
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsRoomViewModel @Inject constructor(private val newsRoomRepository: NewsRoomRepository) :
    ViewModel() {

    val insetStattus = MutableLiveData<String>()
    fun insertNews(articles: Articles) {
        newsRoomRepository.insertArticle(articles,
            { success -> insetStattus.value = (success) },
            { error -> insetStattus.value = (error) })
    }

    val localNewsList = MutableLiveData<List<Articles>?>()
    fun getLocalNews() {
        viewModelScope.launch(Dispatchers.IO) {
            newsRoomRepository.getLocalNews(
                { success -> localNewsList.postValue(success)},
                { error -> localNewsList.postValue(null) })
        }
    }

    val deleteRequest = MutableLiveData<Int>()
    fun deleteNews(tittle:String) {
        viewModelScope.launch(Dispatchers.IO) {
            newsRoomRepository.deleteNews(tittle,
                { success -> deleteRequest.postValue(success)},
                { error -> deleteRequest.postValue(error) })
        }
    }

}