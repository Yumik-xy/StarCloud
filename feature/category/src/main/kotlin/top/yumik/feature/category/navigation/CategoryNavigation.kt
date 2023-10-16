package top.yumik.feature.category.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import top.yumik.feature.category.CategoryRoute

const val categoryNavigationRoute = "category_route"

fun NavController.navigationToCategory(navOptions: NavOptions? = null) {
    this.navigate(categoryNavigationRoute, navOptions)
}

fun NavGraphBuilder.categoryScreen(
    navigateToLogin: () -> Unit
) {
    composable(
        route = categoryNavigationRoute
    ) {
        CategoryRoute(
            modifier = Modifier.fillMaxSize(),
            onNavigateToLogin = navigateToLogin
        )
    }
}