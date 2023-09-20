package top.yumik.core.designsystem.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import top.yumik.core.designsystem.image.ScIcons
import top.yumik.core.designsystem.preview.ThemePreviews
import top.yumik.core.designsystem.theme.ScTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    navigationIcon: ImageVector? = null,
    navigationIconContentDescription: String? = null,
    actionIcon: ImageVector? = null,
    actionIconContentDescription: String? = null,
    colors: TopAppBarColors = ScTopAppBarDefaults.centerAlignedTopAppBarColors(),
    onNavigationClick: () -> Unit = {},
    onActionClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            if (navigationIcon != null) {
                IconButton(onClick = onNavigationClick) {
                    Icon(
                        imageVector = navigationIcon,
                        contentDescription = navigationIconContentDescription,
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        },
        actions = {
            if (actionIcon != null) {
                IconButton(onClick = onActionClick) {
                    Icon(
                        imageVector = actionIcon,
                        contentDescription = actionIconContentDescription,
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        },
        colors = colors,
        modifier = modifier
    )
}

object ScTopAppBarDefaults {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun centerAlignedTopAppBarColors() = TopAppBarDefaults.centerAlignedTopAppBarColors()
}

@OptIn(ExperimentalMaterial3Api::class)
@ThemePreviews
@Composable
private fun ScTopAppBarPreview() {
    ScTheme {
        ScTopAppBar(
            title = stringResource(id = android.R.string.untitled),
            navigationIcon = ScIcons.Search,
            navigationIconContentDescription = "Navigation icon",
            actionIcon = ScIcons.MoreVert,
            actionIconContentDescription = "Action icon",
        )
    }
}