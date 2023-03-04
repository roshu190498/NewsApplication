package com.example.newsapplication.home.ui

import android.app.Dialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.R
import com.example.newsapplication.common.Status
import com.example.newsapplication.common.openActivity
import com.example.newsapplication.database.NewsRoomViewModel
import com.example.newsapplication.databinding.ActivityHomeBinding
import com.example.newsapplication.home.adapter.NewsListAdapter
import com.example.newsapplication.home.model.Articles
import com.example.newsapplication.home.viewmodel.HomeViewModel
import com.example.newsapplication.localnews.ui.LocalNewsActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeActivity : AppCompatActivity(),NewsListAdapter.NewsItemCalls{
    @Inject
    lateinit var progressDialog: Dialog

    lateinit var binding : ActivityHomeBinding

    private val homeViewModel : HomeViewModel by viewModels()
    private val newsRoomViewModel : NewsRoomViewModel by viewModels()

    private val newsListAdapter by lazy {
        NewsListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.tlToolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id: Int = item.getItemId()
        if (id == R.id.go_to_local) {
            openActivity(LocalNewsActivity::class.java)
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onStart() {
        super.onStart()
        initobserver()
        initviews()
        setClicks()
    }

    private fun setClicks() {
        binding.tvRetry.setOnClickListener {
            homeViewModel.getTopHeadLine()
        }
    }

    private fun initobserver() {
        homeViewModel.topHeadlines.observe(this@HomeActivity) {
            when (it.status) {
                Status.SUCCESS -> {
                    progressDialog.dismiss()
                    //set up adapter
                    it.data?.articles?.let {
                        newsListAdapter.setDataToNewsList(it,this)
                        binding.rvNewsList.scrollToPosition(0)
                    }
                    binding.vwHomePage.displayedChild=0
                }
                Status.ERROR -> {
                    progressDialog.dismiss()
                    binding.vwHomePage.displayedChild=1
                }
                Status.LOADING -> {
                    progressDialog.show()
                }
            }
        }
    }

    private fun initviews() {
        setupAdapter()
        homeViewModel.getTopHeadLine()
    }

    private fun setupAdapter() {
        binding.rvNewsList.setLayoutManager(object : LinearLayoutManager(
            this,
            HORIZONTAL,
            false
        ) {
            override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
                return true
            }
        })
        binding.rvNewsList.adapter=newsListAdapter
    }

    override fun addtoLocal(data: Articles) {
        newsRoomViewModel.insertNews(data)
    }

    override fun redirectToUrl(url: String) {

    }
}