package com.example.mynewsappcleanarch.mealz.presentation.news_home.domain

import com.example.mynewsappcleanarch.mealz.presentation.news_home.data.reposnse.Article
import com.example.mynewsappcleanarch.mealz.presentation.news_home.data.reposnse.NewsResponse

interface NewsRepository {
suspend fun getNews ():NewsResponse
}