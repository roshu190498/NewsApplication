package com.example.newsapplication.localnews.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsapplication.databinding.ActivityLocalNewsBinding

class LocalNewsActivity : AppCompatActivity() {
    lateinit var binding : ActivityLocalNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocalNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}