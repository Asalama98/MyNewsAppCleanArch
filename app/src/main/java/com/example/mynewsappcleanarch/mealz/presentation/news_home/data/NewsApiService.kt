package com.example.mynewsappcleanarch.mealz.presentation.news_home.data

import com.example.mynewsappcleanarch.mealz.presentation.news_home.data.reposnse.NewsResponse
import com.hamalawey.mealz.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country:String="us",
        @Query("apiKey") apikey:String= Constants.API_KEY

    ): NewsResponse
}