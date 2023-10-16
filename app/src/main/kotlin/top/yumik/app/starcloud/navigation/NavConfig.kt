package top.yumik.app.starcloud.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import top.yumik.app.starcloud.R
import top.yumik.core.designsystem.image.ScIcons
import top.yumik.feature.category.navigation.categoryNavigationRoute
import top.yumik.feature.manager.navigation.managerNavigationRoute

enum class NavConfig(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val titleRes: Int,
    val route: String
) {
    CATEGORY(
        selectedIcon = ScIcons.Category,
        unselectedIcon = ScIcons.CategoryBorder,
        titleRes = R.string.nac_config_title_category,
        route = categoryNavigationRoute
    ),
    MUSIC(
        selectedIcon = ScIcons.Album,
        unselectedIcon = ScIcons.AlbumBorder,
        titleRes = R.string.nac_config_title_music,
        route = ""
    ),
    MANAGER(
        selectedIcon = ScIcons.Home,
        unselectedIcon = ScIcons.HomeBorder,
        titleRes = R.string.nac_config_title_manager,
        route = managerNavigationRoute
    );

    companion object {
        fun fromRoute(route: String?): NavConfig? =
            NavConfig.values().firstOrNull { it.route == route }
    }
}