package top.yumik.core.designsystem.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import top.yumik.core.designsystem.image.ScIcons
import top.yumik.core.designsystem.preview.ScPreview
import top.yumik.core.designsystem.preview.ThemePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScBottomNavBar(
    modifier: Modifier = Modifier,
    colors: ScBottomNavBarColors = ScBottomNavBarDefaults.bottomNavBarColors(),
    content: @Composable RowScope.() -> Unit
) {
    NavigationBar(
        modifier = modifier,
        containerColor = colors.containerColor,
        contentColor = colors.contentColor,
        tonalElevation = colors.tonalElevation,
        content = content
    )
}

@Composable
fun RowScope.ScBottomNavBarItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: () -> Unit,
    unselectedIcon: ImageVector,
    selectedIcon: ImageVector = unselectedIcon,
    iconContentDescription: String?,
    label: String,
    colors: NavigationBarItemColors = NavigationBarItemDefaults.colors()
) {
    NavigationBarItem(
        modifier = modifier,
        selected = selected,
        onClick = onClick,
        icon = {
            ScSelectedIcon(
                isSelected = selected,
                selectedIcon = selectedIcon,
                unselectedIcon = unselectedIcon,
                contentDescription = iconContentDescription
            )
        },
        label = {
            Text(text = label)
        },
        colors = colors
    )
}

object ScBottomNavBarDefaults {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun bottomNavBarColors(
        containerColor: Color = MaterialTheme.colorScheme.surface,
        contentColor: Color = MaterialTheme.colorScheme.contentColorFor(containerColor),
        tonalElevation: Dp = 2.dp
    ): ScBottomNavBarColors {
        return ScBottomNavBarColors(
            containerColor = containerColor,
            contentColor = contentColor,
            tonalElevation = tonalElevation
        )
    }
}

@ExperimentalMaterial3Api
@Stable
class ScBottomNavBarColors internal constructor(
    internal val containerColor: Color,
    internal val contentColor: Color,
    internal val tonalElevation: Dp
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@ThemePreviews
fun ScBottomNavBarUnSelectedPreview() {
    ScPreview {
        ScBottomNavBar {
            ScBottomNavBarItem(
                selected = false,
                onClick = { /*TODO*/ },
                unselectedIcon = ScIcons.CategoryBorder,
                selectedIcon = ScIcons.Category,
                iconContentDescription = null,
                label = "乐库"
            )
            ScBottomNavBarItem(
                selected = false,
                onClick = { /*TODO*/ },
                unselectedIcon = ScIcons.AlbumBorder,
                selectedIcon = ScIcons.Album,
                iconContentDescription = null,
                label = "音乐"
            )
            ScBottomNavBarItem(
                selected = false,
                onClick = { /*TODO*/ },
                unselectedIcon = ScIcons.HomeBorder,
                selectedIcon = ScIcons.Home,
                iconContentDescription = null,
                label = "管理"
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@ThemePreviews
fun ScBottomNavBarSelectedPreview() {
    ScPreview {
        ScBottomNavBar {
            ScBottomNavBarItem(
                selected = true,
                onClick = { /*TODO*/ },
                unselectedIcon = ScIcons.CategoryBorder,
                selectedIcon = ScIcons.Category,
                iconContentDescription = null,
                label = "乐库"
            )
            ScBottomNavBarItem(
                selected = true,
                onClick = { /*TODO*/ },
                unselectedIcon = ScIcons.AlbumBorder,
                selectedIcon = ScIcons.Album,
                iconContentDescription = null,
                label = "音乐"
            )
            ScBottomNavBarItem(
                selected = true,
                onClick = { /*TODO*/ },
                unselectedIcon = ScIcons.HomeBorder,
                selectedIcon = ScIcons.Home,
                iconContentDescription = null,
                label = "管理"
            )
        }
    }
}