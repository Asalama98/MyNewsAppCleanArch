package com.example.mynewsappcleanarch.mealz.presentation.news_home.data

import com.example.mynewsappcleanarch.mealz.presentation.news_home.data.NewsApiService
import com.example.mynewsappcleanarch.mealz.presentation.news_home.data.reposnse.Article
import com.example.mynewsappcleanarch.mealz.presentation.news_home.data.reposnse.NewsResponse
import com.example.mynewsappcleanarch.mealz.presentation.news_home.domain.NewsRepository
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor
    (private val api : NewsApiService) : NewsRepository {
    override suspend fun getNews(): NewsResponse{
        return api.getNews()
    }
}