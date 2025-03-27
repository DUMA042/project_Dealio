//package com.example.dealio.navigation
//
//import androidx.annotation.StringRes
//import androidx.compose.ui.graphics.vector.ImageVector
//import com.example.dealio.R
//import com.example.designsystem.icon.PDIcons
//import com.example.designsystem.icon.PDIcons.Home
//import com.example.feature.home.navigation.HomeRoute
//import com.example.feature.home.R as homeR
//
//import kotlin.reflect.KClass
//
//enum class PDNavDestinations(
//    val selectedIcon: ImageVector,
//    val unselectedIcon: ImageVector,
//    @StringRes val iconTextId: Int,
//    @StringRes val titleTextId: Int,
//    val route: KClass<*>,
//    val baseRoute: KClass<*> = route,
//) {
//    HOME(
//        selectedIcon = PDIcons.Upcoming,
//        unselectedIcon = PDIcons.UpcomingBorder,
//        iconTextId = homeR.string.feature_home_Home_Nav,
//        titleTextId = R.string.app_name,
//        route = HomeRoute::class,
//    ),
//    HUB(
//        selectedIcon = PDIcons.Bookmarks,
//        unselectedIcon = PDIcons.BookmarksBorder,
//        iconTextId = bookmarksR.string.feature_bookmarks_title,
//        titleTextId = bookmarksR.string.feature_bookmarks_title,
//        route = BookmarksRoute::class,
//    ),
//    SAVES(
//        selectedIcon = PDIcons.Chat,
//        unselectedIcon = PDIcons.chatBorder,
//        iconTextId = searchR.string.feature_search_interests,
//        titleTextId = searchR.string.feature_search_interests,
//        route = InterestsRoute::class,
//    ),
//}