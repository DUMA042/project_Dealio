package com.example.feature.hub.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.feature.hub.HubScreen
import kotlinx.serialization.Serializable

@Serializable data object  HubRoute

fun NavController.navigateToHub(navOptions: NavOptions) =
    navigate(route = HubRoute, navOptions)

fun NavGraphBuilder.hubBuilder(){
    composable<HubRoute> {
        HubScreen()
    }
}
