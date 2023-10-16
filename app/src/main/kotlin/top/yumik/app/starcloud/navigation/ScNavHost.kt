package top.yumik.app.starcloud.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import top.yumik.app.starcloud.ui.ScAppState
import top.yumik.feature.category.navigation.categoryScreen
import top.yumik.feature.login.navigateToLogin
import top.yumik.feature.manager.navigation.managerScreen

@Composable
fun ScNavHost(
    modifier: Modifier = Modifier,
    appState: ScAppState,
    startDestination: String
) {
    val navController = appState.navController

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        categoryScreen(
            navigateToLogin = ::navigateToLogin
        )
        managerScreen(
            appConfig = appState.appConfig,
            navigateToLogin = ::navigateToLogin
        )
    }
}