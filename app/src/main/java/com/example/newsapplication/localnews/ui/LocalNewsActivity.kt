package com.example.newsapplication.localnews.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.database.NewsRoomViewModel
import com.example.newsapplication.databinding.ActivityLocalNewsBinding
import com.example.newsapplication.home.adapter.NewsListAdapter
import com.example.newsapplication.home.model.Articles
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocalNewsActivity : AppCompatActivity(),NewsListAdapter.NewsItemCalls {
    lateinit var binding : ActivityLocalNewsBinding

    private val newsRoomViewModel : NewsRoomViewModel by viewModels()

    private val localListAdapter by lazy {
        NewsListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocalNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupadapter()
        initObserver()
        callDb()
    }

    private fun setupadapter() {
        binding.rvLocalList.setLayoutManager(object : LinearLayoutManager(
            this,
            HORIZONTAL,
            false
        ) {
            override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
                lp.marginEnd = 20
                return true
            }
        })
        binding.rvLocalList.adapter = localListAdapter
    }

    private fun initObserver() {
        newsRoomViewModel.localNewsList.observe(this@LocalNewsActivity){
            val list = it as ArrayList
            if (!list.isEmpty() ){
                binding.vwHomePage.displayedChild=0
                localListAdapter.setDataToNewsList(
                    it,this@LocalNewsActivity,it
                )
            }else{
                binding.vwHomePage.displayedChild=1
            }
        }

        newsRoomViewModel.deleteRequest.observe(this){
            callDb()
        }
    }

    private fun callDb() {
        newsRoomViewModel.getLocalNews()
    }

    override fun addtoLocal(data: Articles) {
        newsRoomViewModel.deleteNews(data.title?:"")
    }

    override fun redirectToUrl(url: String?) {

    }

}