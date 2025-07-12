package com.example.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable




import com.example.feature.home.HomeScreen


@Serializable  data object HomeRoute

fun NavController.navigateToHome(navOptions: NavOptions) = navigate(HomeRoute, navOptions)


fun NavGraphBuilder.homeBuilder(){
    composable<HomeRoute> {
        HomeScreen()
    }
}



