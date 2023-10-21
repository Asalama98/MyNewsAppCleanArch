package com.example.mynewsappcleanarch.mealz.presentation.news_home.presentaion

import android.util.Log
import com.example.mynewsappcleanarch.mealz.base.BaseViewModel
import com.example.mynewsappcleanarch.mealz.presentation.news_home.data.reposnse.Article
import com.example.mynewsappcleanarch.mealz.presentation.news_home.data.reposnse.NewsResponse
import com.example.mynewsappcleanarch.mealz.presentation.news_home.domain.NewsRepository
import com.hamalawey.mealz.presentation.news_home.presentaion.NewsScreenContract
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): BaseViewModel<NewsScreenContract.Event, NewsScreenContract.State>() {



    override fun setInitialState(): NewsScreenContract.State {
        return NewsScreenContract.State(false)
    }

    override fun handleEvents(event: NewsScreenContract.Event) {
        when(event){
            is NewsScreenContract.Event.DisplayNews -> {
                displayNews()
                Log.e("enterd" ,"enterd")
            }


            else -> {}
        }
    }

    private suspend fun getNews(): NewsResponse {
        return newsRepository.getNews()
    }


    private fun displayNews() {
        var news : NewsResponse? = null
        launchCoroutine {
            viewState.value.loading=true
            news=getNews()
        }.invokeOnCompletion {
            if(it  == null){
                setState {
                    NewsScreenContract.State(
                        newsList = news
                    )
                }
            }
        }
    }

    private fun naviget(){

    }

}