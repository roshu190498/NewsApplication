package com.example.newsapplication.localnews.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.newsapplication.database.NewsRoomViewModel
import com.example.newsapplication.databinding.ActivityLocalNewsBinding
import com.example.newsapplication.home.adapter.NewsListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocalNewsActivity : AppCompatActivity() {
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
        binding.rvLocalList.adapter= localListAdapter
    }

    private fun initObserver() {
        newsRoomViewModel.localNewsList.observe(this@LocalNewsActivity){
            val list = it as ArrayList
            if (!list.isNullOrEmpty() ){
                binding.vwHomePage.displayedChild=0
                localListAdapter.setDataToNewsList(
                    it,null
                )
            }else{
                binding.vwHomePage.displayedChild=1
            }
        }
    }

    private fun callDb() {
        newsRoomViewModel.getLocalNews()
    }

}