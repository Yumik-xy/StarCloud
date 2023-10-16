package top.yumik.app.starcloud.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import top.yumik.app.starcloud.R
import top.yumik.app.starcloud.navigation.NavConfig
import top.yumik.app.starcloud.navigation.ScNavHost
import top.yumik.core.data.util.NetworkMonitor
import top.yumik.core.designsystem.component.ScBackground
import top.yumik.core.designsystem.component.ScBottomNavBar
import top.yumik.core.designsystem.component.ScBottomNavBarItem
import top.yumik.core.designsystem.component.ScRailNavBar
import top.yumik.core.designsystem.component.ScRailNavBarItem
import top.yumik.core.designsystem.component.ScTopAppBar
import top.yumik.core.designsystem.image.ScIcons
import top.yumik.feature.manager.navigation.managerNavigationRoute

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MainScreen(
    windowSizeClass: WindowSizeClass,
    appState: ScAppState = rememberScAppState(
        windowSizeClass = windowSizeClass
    )
) {
    ScBackground {
        Scaffold(contentWindowInsets = WindowInsets(0, 0, 0, 0),
            bottomBar = {
                if (appState.shouldShowBottomBar) {
                    ScNavBottomBar(
                        destinations = appState.navConfigs,
                        onNavigateToRoute = appState::navigateToRoute,
                        currentRoute = appState.currentRoute
                    )
                }
            }
        ) { padding ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .consumeWindowInsets(padding)
                    .windowInsetsPadding(
                        WindowInsets
                            .safeDrawing
                            .only(WindowInsetsSides.Horizontal)
                    )
            ) {
                if (appState.shouldShowNavRail) {
                    ScNavRail(
                        destinations = appState.navConfigs,
                        onNavigateToRoute = appState::navigateToRoute,
                        currentRoute = appState.currentRoute
                    )
                }
                Column(modifier = Modifier.fillMaxSize()) {
                    val currentNavConfig = NavConfig.fromRoute(appState.currentRoute)
                    if (currentNavConfig != null) {
                        ScTopAppBar(
                            title = stringResource(currentNavConfig.titleRes),
                            actionIcon = ScIcons.Search,
                            actionIconContentDescription = stringResource(R.string.content_description_search),
                            onActionClick = {

                            },
                            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
                        )
                    }
                    ScNavHost(
                        appState = appState,
                        startDestination = managerNavigationRoute
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScNavRail(
    modifier: Modifier = Modifier,
    destinations: List<NavConfig>,
    onNavigateToRoute: (NavConfig) -> Unit,
    currentRoute: String?
) {
    ScRailNavBar(
        modifier = modifier
    ) {
        destinations.forEach { navConfig ->
            ScRailNavBarItem(
                selected = currentRoute == navConfig.route,
                onClick = { onNavigateToRoute(navConfig) },
                unselectedIcon = navConfig.unselectedIcon,
                selectedIcon = navConfig.selectedIcon,
                iconContentDescription = stringResource(navConfig.titleRes),
                label = stringResource(navConfig.titleRes)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScNavBottomBar(
    modifier: Modifier = Modifier,
    destinations: List<NavConfig>,
    onNavigateToRoute: (NavConfig) -> Unit,
    currentRoute: String?
) {
    ScBottomNavBar(
        modifier = modifier
    ) {
        destinations.forEach { navConfig ->
            ScBottomNavBarItem(
                selected = currentRoute == navConfig.route,
                onClick = { onNavigateToRoute(navConfig) },
                unselectedIcon = navConfig.unselectedIcon,
                selectedIcon = navConfig.selectedIcon,
                iconContentDescription = stringResource(navConfig.titleRes),
                label = stringResource(navConfig.titleRes)
            )
        }
    }
}