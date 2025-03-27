//package com.example.dealio.navigation
//
//import androidx.annotation.StringRes
//import androidx.compose.ui.graphics.vector.ImageVector
//import com.example.dealio.R
//import com.example.designsystem.icon.PDIcons
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
//        iconTextId = forYouR.string.feature_foryou_title,
//        titleTextId = R.string.app_name,
//        route = ForYouRoute::class,
//        baseRoute = ForYouBaseRoute::class,
//    ),
//    FORUM(
//        selectedIcon = NiaIcons.Bookmarks,
//        unselectedIcon = NiaIcons.BookmarksBorder,
//        iconTextId = bookmarksR.string.feature_bookmarks_title,
//        titleTextId = bookmarksR.string.feature_bookmarks_title,
//        route = BookmarksRoute::class,
//    ),
//    SAVES(
//        selectedIcon = NiaIcons.Grid3x3,
//        unselectedIcon = NiaIcons.Grid3x3,
//        iconTextId = searchR.string.feature_search_interests,
//        titleTextId = searchR.string.feature_search_interests,
//        route = InterestsRoute::class,
//    ),
//}