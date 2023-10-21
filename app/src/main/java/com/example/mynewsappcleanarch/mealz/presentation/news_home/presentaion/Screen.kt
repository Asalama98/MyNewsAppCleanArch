package com.example.mynewsappcleanarch.mealz.presentation.news_home.presentaion

sealed class Screen(val roure : String)  {
    object NewsList: Screen("News_List_Screen")
    object NewsDetails: Screen("News_details_Screen")

}