package com.rs.tmobiledemosample.core.navigation

import MainScreen
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.rs.tmobiledemosample.presentation.ui.AuthScreen
import com.rs.tmobiledemosample.presentation.ui.LaunchScreen
import com.rs.tmobiledemosample.presentation.ui.MapScreen
import com.rs.tmobiledemosample.presentation.ui.StudentScreen


/**
 * Created by shankar
 * Navhelper defines to navigate composables
 */
object NavHelper {

    private fun navigateTo(navController: NavController, route: String) {
        when {

            route == Screen.StartToLaunchScreen.route -> {
                navController.navigate(Screen.StartToLaunchScreen.route)
            }
            route == Screen.AuthToMainScreen.route -> {
                navController.navigate(Screen.AuthToMainScreen.route){
                    popUpTo(Screen.LaunchToAuthScreen.route){
                        inclusive = true
                    }
                }
            }
            route == Screen.MaintoStudentScreen.route -> {
                navController.navigate(Screen.MaintoStudentScreen.route){

                }
            }
            route==Screen.StudentScreenToMapScreen.route ->{
                navController.navigate(Screen.StudentScreenToMapScreen.route)
            }

            route == Screen.LaunchToAuthScreen.route -> {
                navController.navigate(Screen.LaunchToAuthScreen.route){
                    popUpTo(Screen.StartToLaunchScreen.route){
                        inclusive = true
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun SetupNavGraph(navController: NavHostController) {
        AnimatedNavHost(modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = Screen.StartToLaunchScreen.route){

            composable(Screen.StartToLaunchScreen.route) {
                LaunchScreen(
                    onNavigateTo = {
                        navigateTo(navController,it)
                    }
                )
            }

            composable(Screen.LaunchToAuthScreen.route) {
                AuthScreen(
                    onNavigateTo = {
                        navigateTo(navController,it)
                    }
                )
            }
            composable(Screen.AuthToMainScreen.route) {
                MainScreen(
                    onNavigateTo = {
                        navigateTo(navController,it)
                    }
                )
            }
            composable(Screen.MaintoStudentScreen.route) {
                StudentScreen(
                    onNavigateTo = {
                        navigateTo(navController,it)
                    }
                )
            }
            composable(Screen.StudentScreenToMapScreen.route) {
                MapScreen(
                    onNavigateTo = {
                        navigateTo(navController,it)
                    }
                )
            }
        }
    }
}