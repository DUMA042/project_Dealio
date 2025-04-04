package com.example.dealio.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.dealio.R
import com.example.designsystem.icon.PDIcons
import com.example.feature.home.navigation.HomeRoute
import com.example.feature.hub.navigation.HubRoute
import com.example.feature.saves.navigation.SaveRoute
import com.example.feature.home.R as homeR
import com.example.feature.hub.R as hubR
import com.example.feature.saves.R as savesR

import kotlin.reflect.KClass

enum class PDNavDestinations(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val iconTextId: Int,
    @StringRes val titleTextId: Int,
    val route: KClass<*>,
) {
    HOME(
        selectedIcon = PDIcons.Upcoming,
        unselectedIcon = PDIcons.UpcomingBorder,
        iconTextId = homeR.string.feature_home_Home_Nav,
        titleTextId = R.string.app_name,
        route = HomeRoute::class,
    ),
    HUB(
        selectedIcon = PDIcons.Bookmarks,
        unselectedIcon = PDIcons.BookmarksBorder,
        iconTextId = hubR.string.feature_hub_Hub_Nav,
        titleTextId = hubR.string.feature_hub_Hub_Nav,
        route = HubRoute::class,
    ),
    SAVES(
        selectedIcon = PDIcons.Chat,
        unselectedIcon = PDIcons.chatBorder,
        iconTextId = savesR.string.feature_saves_saves_Nav,
        titleTextId = savesR.string.feature_saves_saves_Nav,
        route = SaveRoute::class,
    ),
}