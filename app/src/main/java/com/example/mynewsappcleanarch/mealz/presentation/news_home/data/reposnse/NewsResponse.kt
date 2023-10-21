package com.example.mynewsappcleanarch.mealz.presentation.news_home.data.reposnse


data class NewsResponse(
    val articles: List<Article?>?,
    val status: String?,
    val totalResults: Int?
)