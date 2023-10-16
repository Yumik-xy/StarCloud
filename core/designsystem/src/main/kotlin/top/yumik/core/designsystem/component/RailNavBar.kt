package top.yumik.core.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemColors
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import top.yumik.core.designsystem.image.ScIcons
import top.yumik.core.designsystem.preview.ScPreview
import top.yumik.core.designsystem.preview.ThemePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScRailNavBar(
    modifier: Modifier = Modifier,
    colors: ScRailNavBarColors = ScRailNavBarDefaults.railNavBarColors(),
    content: @Composable ColumnScope.() -> Unit
) {
    NavigationRail(
        modifier = modifier,
        containerColor = colors.containerColor,
        contentColor = colors.contentColor
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(
                space = 16.dp,
                alignment = Alignment.CenterVertically
            ),
            content = content
        )
    }
}

@Composable
fun ColumnScope.ScRailNavBarItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: () -> Unit,
    unselectedIcon: ImageVector,
    selectedIcon: ImageVector = unselectedIcon,
    iconContentDescription: String?,
    label: String,
    colors: NavigationRailItemColors = NavigationRailItemDefaults.colors()
) {
    NavigationRailItem(
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

object ScRailNavBarDefaults {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun railNavBarColors(
        containerColor: Color = MaterialTheme.colorScheme.surface,
        contentColor: Color = MaterialTheme.colorScheme.contentColorFor(containerColor),
    ): ScRailNavBarColors {
        return ScRailNavBarColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    }
}

@ExperimentalMaterial3Api
@Stable
class ScRailNavBarColors internal constructor(
    internal val containerColor: Color,
    internal val contentColor: Color
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@ThemePreviews
fun ScRailNavBarUnSelectedPreview() {
    ScPreview {
        ScRailNavBar {
            ScRailNavBarItem(
                selected = false,
                onClick = { /*TODO*/ },
                unselectedIcon = ScIcons.CategoryBorder,
                selectedIcon = ScIcons.Category,
                iconContentDescription = null,
                label = "广场"
            )
            ScRailNavBarItem(
                selected = false,
                onClick = { /*TODO*/ },
                unselectedIcon = ScIcons.AlbumBorder,
                selectedIcon = ScIcons.Album,
                iconContentDescription = null,
                label = "音乐"
            )
            ScRailNavBarItem(
                selected = false,
                onClick = { /*TODO*/ },
                unselectedIcon = ScIcons.HomeBorder,
                selectedIcon = ScIcons.Home,
                iconContentDescription = null,
                label = "账号"
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@ThemePreviews
fun ScRailNavBarSelectedPreview() {
    ScPreview {
        ScRailNavBar {
            ScRailNavBarItem(
                selected = true,
                onClick = { /*TODO*/ },
                unselectedIcon = ScIcons.CategoryBorder,
                selectedIcon = ScIcons.Category,
                iconContentDescription = null,
                label = "广场"
            )
            ScRailNavBarItem(
                selected = true,
                onClick = { /*TODO*/ },
                unselectedIcon = ScIcons.AlbumBorder,
                selectedIcon = ScIcons.Album,
                iconContentDescription = null,
                label = "音乐"
            )
            ScRailNavBarItem(
                selected = true,
                onClick = { /*TODO*/ },
                unselectedIcon = ScIcons.HomeBorder,
                selectedIcon = ScIcons.Home,
                iconContentDescription = null,
                label = "账号"
            )
        }
    }
}