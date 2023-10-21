package com.example.mynewsappcleanarch.mealz.presentation.news_home.presentaion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mynewsappcleanarch.ui.theme.MyNewsAppCleanArchTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyNewsAppCleanArchTheme {
                // A surface container using the 'background' color from the theme

                val navController = rememberNavController()
                    
                NavHost(navController = navController, startDestination = Screen.NewsList.roure ){
                    composable(
                        route=Screen.NewsList.roure
                    ){
                        NewsScreen(navController = navController)
                    }
                }   
            }
        }
    }
}




