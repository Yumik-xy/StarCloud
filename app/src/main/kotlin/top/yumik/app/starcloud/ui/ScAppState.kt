package top.yumik.app.starcloud.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import kotlinx.coroutines.CoroutineScope
import top.yumik.app.starcloud.BuildConfig
import top.yumik.app.starcloud.navigation.NavConfig
import top.yumik.core.model.AppConfig
import top.yumik.feature.category.navigation.navigationToCategory
import top.yumik.feature.manager.navigation.navigationToManager

@Composable
fun rememberScAppState(
    windowSizeClass: WindowSizeClass,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
): ScAppState {
    return remember(
        windowSizeClass,
        coroutineScope,
        navController,
    ) {
        ScAppState(
            navController = navController,
            windowSizeClass = windowSizeClass,
            coroutineScope = coroutineScope
        )
    }
}

class ScAppState(
    val windowSizeClass: WindowSizeClass,
    coroutineScope: CoroutineScope,
    val navController: NavHostController
) {

    val appConfig: AppConfig = AppConfig(
        isDebug = BuildConfig.DEBUG,
        versionCode = BuildConfig.VERSION_CODE,
        versionName = BuildConfig.VERSION_NAME,
        buildTime = BuildConfig.BUILD_TIME,
        openSourceLink = BuildConfig.GITHUB_LINK
    )

    val currentRoute: String?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route

    val navConfigs: List<NavConfig> = NavConfig.values().asList()

    val shouldShowBottomBar: Boolean
        get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

    val shouldShowNavRail: Boolean
        get() = !shouldShowBottomBar

    fun navigateToRoute(navConfig: NavConfig) {
        val options = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
        when (navConfig) {
            NavConfig.CATEGORY -> navController.navigationToCategory(options)
            NavConfig.MUSIC -> Unit
            NavConfig.MANAGER -> navController.navigationToManager(options)
        }
    }
}