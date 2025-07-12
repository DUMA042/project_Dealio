package com.example.feature.saves.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.feature.saves.SaveScreen
import kotlinx.serialization.Serializable

@Serializable data object SaveRoute


fun NavController.navigateToSave(navOptions: NavOptions) =
    navigate(route = SaveRoute, navOptions)

fun NavGraphBuilder.saveBuilder(){
    composable<SaveRoute> {
        SaveScreen()
    }
}

