package com.example.newsapplication.home.module

import com.example.newsapplication.home.api.HomeApis
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
class HomeModule {
    @Provides
    fun providesHomeApis(retrofit: Retrofit) : HomeApis = retrofit.create(HomeApis::class.java)

}