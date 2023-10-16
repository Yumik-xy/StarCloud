package top.yumik.core.designsystem.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import top.yumik.core.designsystem.image.ScIcons
import top.yumik.core.designsystem.preview.ScPreview
import top.yumik.core.designsystem.preview.ThemePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    navigationIcon: ImageVector? = null,
    navigationIconContentDescription: String? = null,
    actionIcon: ImageVector? = null,
    actionIconContentDescription: String? = null,
    colors: TopAppBarColors = ScTopAppBarDefaults.topAppBarColors(),
    onNavigationClick: () -> Unit = {},
    onActionClick: () -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    @Composable
    fun action(): @Composable (RowScope.() -> Unit) = {
        if (actionIcon != null) {
            IconButton(onClick = onActionClick) {
                Icon(
                    imageVector = actionIcon,
                    contentDescription = actionIconContentDescription,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }

    @Composable
    fun navigationIcon(): @Composable (() -> Unit) = {
        if (navigationIcon != null) {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    imageVector = navigationIcon,
                    contentDescription = navigationIconContentDescription,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }

    if (navigationIcon == null) {
        TopAppBar(
            title = { Text(text = title) },
            navigationIcon = navigationIcon(),
            actions = action(),
            colors = colors,
            modifier = modifier,
            scrollBehavior = scrollBehavior
        )
    } else {
        CenterAlignedTopAppBar(
            title = { Text(text = title) },
            navigationIcon = navigationIcon(),
            actions = action(),
            colors = colors,
            modifier = modifier,
            scrollBehavior = scrollBehavior
        )
    }
}

object ScTopAppBarDefaults {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun topAppBarColors() = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = Color.Transparent,
        scrolledContainerColor = Color.Transparent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@ThemePreviews
@Composable
private fun ScCenterAlignedTopAppBarPreview() {
    ScPreview {
        ScTopAppBar(
            title = stringResource(id = android.R.string.untitled),
            navigationIcon = ScIcons.Search,
            navigationIconContentDescription = "Navigation icon",
            actionIcon = ScIcons.MoreVert,
            actionIconContentDescription = "Action icon",
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@ThemePreviews
@Composable
private fun ScTopAppBarPreview() {
    ScPreview {
        ScTopAppBar(
            title = stringResource(id = android.R.string.untitled),
            actionIcon = ScIcons.MoreVert,
            actionIconContentDescription = "Action icon",
        )
    }
}