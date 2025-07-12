package com.example.feature.scancode.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.feature.scancode.ScanCodeScreen
import kotlinx.serialization.Serializable

@Serializable  data object ScanCodeRoute

fun NavController.navigateToScanCode(navOptions: NavOptions) = navigate(ScanCodeRoute, navOptions)


fun NavGraphBuilder.homeBuilder(){
    composable<ScanCodeRoute> {
        ScanCodeScreen()
    }
}