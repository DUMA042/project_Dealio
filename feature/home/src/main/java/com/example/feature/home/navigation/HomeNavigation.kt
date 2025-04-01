package com.example.feature.home.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable


import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController



import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController


import androidx.navigation.compose.navigation
import androidx.navigation.navOptions

import kotlinx.coroutines.delay
import kotlin.reflect.KClass


@Serializable  data object HomeRoute

//fun NavController.HomeRotue(navOptions: NavOptions) = navigate(HomeRoute, navOptions)

//fun NavController.navigateToHub(navOptions: NavOptions) =
//    navigate(route = HomeRoute, navOptions)
//
//fun NavGraphBuilder.bookmarksScreen(){
//    composable<HomeRoute> {
//        Gg()
//    }
//}

@Composable
fun Gg(){
    Text(text = "gg")
}
