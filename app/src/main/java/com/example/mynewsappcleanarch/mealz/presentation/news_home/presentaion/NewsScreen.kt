package com.example.mynewsappcleanarch.mealz.presentation.news_home.presentaion

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.mynewsappcleanarch.mealz.presentation.news_home.data.reposnse.Article
import com.hamalawey.mealz.presentation.news_home.presentaion.NewsScreenContract

@Composable
fun NewsScreen(
    navController: NavController,
    mYviewmodel : NewViewModel =  hiltViewModel()

){
    val state = mYviewmodel.viewState.value

    Box(modifier = Modifier.fillMaxSize().background(Color.LightGray) ){

        LaunchedEffect(key1 = null){
            mYviewmodel.setEvent(
                NewsScreenContract.Event.DisplayNews

            )

        }
        LazyColumn(
            modifier = Modifier.padding(15.dp)
        ) {
        //    Log.e("titleArticleisis",""+state.newsList)

            items(state.newsList?.articles?: emptyList()){ article->
                NewsItem(article =article!! ,
                    onItemClcked = {
                        navController.navigate(Screen.NewsDetails.roure + "/${article.source?.id}")
                    }
                )
            }


        }
    }


}


@Composable
fun NewsItem (

    article: Article,
    onItemClcked :  (Article) -> Unit
){


    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClcked(article)
            }
            .padding(15.dp)
    , horizontalArrangement = Arrangement.SpaceBetween

            ){

        val painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = article.urlToImage).apply(block = fun ImageRequest.Builder.() {
                transformations(CircleCropTransformation())
            }).build()
        )


        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = article.title!!,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary
        )
    }
}


