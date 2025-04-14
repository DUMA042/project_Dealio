//package com.example.dealio
//
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.rememberCoroutineScope
//import kotlinx.coroutines.CoroutineScope
//
//
//@Composable
//fun rememberNiaAppState(
//    networkMonitor: NetworkMonitor,
//    userNewsResourceRepository: UserNewsResourceRepository,
//    timeZoneMonitor: TimeZoneMonitor,
//    coroutineScope: CoroutineScope = rememberCoroutineScope(),
//    navController: NavHostController = rememberNavController(),
//): NiaAppState {
//    NavigationTrackingSideEffect(navController)
//    return remember(
//        navController,
//        coroutineScope,
//        networkMonitor,
//        userNewsResourceRepository,
//        timeZoneMonitor,
//    ) {
//        NiaAppState(
//            navController = navController,
//            coroutineScope = coroutineScope,
//            networkMonitor = networkMonitor,
//            userNewsResourceRepository = userNewsResourceRepository,
//            timeZoneMonitor = timeZoneMonitor,
//        )
//    }
//}
//
//@Stable
//class NiaAppState(
//    val navController: NavHostController,
//    coroutineScope: CoroutineScope,
//    networkMonitor: NetworkMonitor,
//    userNewsResourceRepository: UserNewsResourceRepository,
//    timeZoneMonitor: TimeZoneMonitor,
//) {
//    private val previousDestination = mutableStateOf<NavDestination?>(null)
//
//    val currentDestination: NavDestination?
//        @Composable get() {
//            // Collect the currentBackStackEntryFlow as a state
//            val currentEntry = navController.currentBackStackEntryFlow
//                .collectAsState(initial = null)
//
//            // Fallback to previousDestination if currentEntry is null
//            return currentEntry.value?.destination.also { destination ->
//                if (destination != null) {
//                    previousDestination.value = destination
//                }
//            } ?: previousDestination.value
//        }
//
//    val currentTopLevelDestination: TopLevelDestination?
//        @Composable get() {
//            return TopLevelDestination.entries.firstOrNull { topLevelDestination ->
//                currentDestination?.hasRoute(route = topLevelDestination.route) == true
//            }
//        }
//
//    val isOffline = networkMonitor.isOnline
//        .map(Boolean::not)
//        .stateIn(
//            scope = coroutineScope,
//            started = SharingStarted.WhileSubscribed(5_000),
//            initialValue = false,
//        )
//
//    /**
//     * Map of top level destinations to be used in the TopBar, BottomBar and NavRail. The key is the
//     * route.
//     */
//    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries
//
//    /**
//     * The top level destinations that have unread news resources.
//     */
//    val topLevelDestinationsWithUnreadResources: StateFlow<Set<TopLevelDestination>> =
//        userNewsResourceRepository.observeAllForFollowedTopics()
//            .combine(userNewsResourceRepository.observeAllBookmarked()) { forYouNewsResources, bookmarkedNewsResources ->
//                setOfNotNull(
//                    FOR_YOU.takeIf { forYouNewsResources.any { !it.hasBeenViewed } },
//                    BOOKMARKS.takeIf { bookmarkedNewsResources.any { !it.hasBeenViewed } },
//                )
//            }
//            .stateIn(
//                coroutineScope,
//                SharingStarted.WhileSubscribed(5_000),
//                initialValue = emptySet(),
//            )
//
//    val currentTimeZone = timeZoneMonitor.currentTimeZone
//        .stateIn(
//            coroutineScope,
//            SharingStarted.WhileSubscribed(5_000),
//            TimeZone.currentSystemDefault(),
//        )
//
//    /**
//     * UI logic for navigating to a top level destination in the app. Top level destinations have
//     * only one copy of the destination of the back stack, and save and restore state whenever you
//     * navigate to and from it.
//     *
//     * @param topLevelDestination: The destination the app needs to navigate to.
//     */
//    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
//        trace("Navigation: ${topLevelDestination.name}") {
//            val topLevelNavOptions = navOptions {
//                // Pop up to the start destination of the graph to
//                // avoid building up a large stack of destinations
//                // on the back stack as users select items
//                popUpTo(navController.graph.findStartDestination().id) {
//                    saveState = true
//                }
//                // Avoid multiple copies of the same destination when
//                // reselecting the same item
//                launchSingleTop = true
//                // Restore state when reselecting a previously selected item
//                restoreState = true
//            }
//
//            when (topLevelDestination) {
//                FOR_YOU -> navController.navigateToForYou(topLevelNavOptions)
//                BOOKMARKS -> navController.navigateToBookmarks(topLevelNavOptions)
//                INTERESTS -> navController.navigateToInterests(null, topLevelNavOptions)
//            }
//        }
//    }
//
//    fun navigateToSearch() = navController.navigateToSearch()
//}
//
///**
// * Stores information about navigation events to be used with JankStats
// */
//@Composable
//private fun NavigationTrackingSideEffect(navController: NavHostController) {
//    TrackDisposableJank(navController) { metricsHolder ->
//        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
//            metricsHolder.state?.putState("Navigation", destination.route.toString())
//        }
//
//        navController.addOnDestinationChangedListener(listener)
//
//        onDispose {
//            navController.removeOnDestinationChangedListener(listener)
//        }
//    }
//}