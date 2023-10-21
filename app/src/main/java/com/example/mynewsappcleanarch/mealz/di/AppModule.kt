package com.example.mynewsappcleanarch.mealz.di

import com.example.mynewsappcleanarch.mealz.presentation.news_home.data.NewsApiService
import com.example.mynewsappcleanarch.mealz.presentation.news_home.data.NewsRepositoryImp
import com.example.mynewsappcleanarch.mealz.presentation.news_home.domain.NewsRepository
import com.hamalawey.mealz.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsApi() : NewsApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideNewsRepository(api: NewsApiService) : NewsRepository
    {
        return NewsRepositoryImp(api)
    }


}