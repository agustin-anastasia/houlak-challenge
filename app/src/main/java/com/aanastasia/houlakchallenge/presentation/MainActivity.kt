package com.aanastasia.houlakchallenge.presentation

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aanastasia.houlakchallenge.MainViewModel
import com.aanastasia.houlakchallenge.presentation.screen.artist.ArtistDetailScreen
import com.aanastasia.houlakchallenge.presentation.screen.home.HomeScreen
import com.aanastasia.houlakchallenge.presentation.theme.HoulakChallengeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HoulakChallengeTheme(
                darkTheme = false
            ) {
                HoulakChallengeApp()
            }
        }
    }
}

@Composable
fun HoulakChallengeApp(){
    val navController = rememberNavController()
    HoulakChallengeTheme() {
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier
                .fillMaxSize(),
        ){
            composable(route = "home"){
                HomeScreen(
                    onArtistSelected = {
                        navController.navigate(route = "artist-detail/$it")
                    }
                )
            }
            composable(
                route = "artist-detail/{id}",
                arguments = listOf(
                    navArgument("id"){ type = NavType.StringType }
                )
            ){
                ArtistDetailScreen()
            }
        }
    }

}