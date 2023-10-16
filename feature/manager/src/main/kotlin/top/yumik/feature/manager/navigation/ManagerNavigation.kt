package top.yumik.feature.manager.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import top.yumik.core.model.AppConfig
import top.yumik.feature.manager.ManagerRoute

const val managerNavigationRoute = "manager_route"

fun NavController.navigationToManager(navOptions: NavOptions? = null) {
    this.navigate(managerNavigationRoute, navOptions)
}

fun NavGraphBuilder.managerScreen(
    appConfig: AppConfig,
    navigateToLogin: () -> Unit
) {
    composable(
        route = managerNavigationRoute
    ) {
        ManagerRoute(
            modifier = Modifier.fillMaxSize(),
            appConfig = appConfig,
            navigateToLogin = navigateToLogin
        )
    }
}