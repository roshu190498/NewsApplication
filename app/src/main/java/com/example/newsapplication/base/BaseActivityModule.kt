package com.example.newsapplication.base

import android.app.Dialog
import android.content.Context
import com.example.newsapplication.utility.Utility
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
class BaseActivityModule {
    @Provides
    fun getProgressBar(@ActivityContext context: Context) : Dialog = Utility.ShowCommonProgressDialog(context)

}