package com.hamalawey.mealz.presentation.news_home.presentaion

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.mynewsappcleanarch.mealz.base.ViewEvent
import com.example.mynewsappcleanarch.mealz.base.ViewSideEffect
import com.example.mynewsappcleanarch.mealz.base.ViewState
import com.example.mynewsappcleanarch.mealz.presentation.news_home.data.reposnse.Article
import com.example.mynewsappcleanarch.mealz.presentation.news_home.data.reposnse.NewsResponse


class NewsScreenContract {

    /*Event
    * 1- display news
    *
    * */

    sealed class Event : ViewEvent {
        object DisplayNews : Event()
    }

    sealed class SideEffects : ViewSideEffect {

        object ToDetailsScreen :SideEffects()

    }

    class State  : ViewState {
        override var loading: Boolean by mutableStateOf(false)
        var newsList :NewsResponse ? by mutableStateOf(null)

        constructor(
            loading :Boolean =false,
            newsList :NewsResponse? = null
        ){
            this.loading=loading
            this.newsList=newsList
        }

    }


}


//Event
//State
//Contract