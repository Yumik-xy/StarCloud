package top.yumik.core.designsystem.component

import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import top.yumik.core.designsystem.image.ScIcons
import top.yumik.core.designsystem.preview.ThemePreviews
import top.yumik.core.designsystem.theme.ScTheme

@Composable
fun ScIconToggleButton(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    icon: ImageVector,
    checkedIcon: ImageVector,
    contentDescription: String?,
    colors: IconToggleButtonColors = ScIconButtonDefaults.iconToggleButtonColors(),
    enabled: Boolean = true,
) {
    FilledIconToggleButton(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        enabled = enabled,
        colors = colors,
    ) {
        Icon(
            imageVector = if (checked) checkedIcon else icon,
            contentDescription = contentDescription
        )
    }
}

object ScIconButtonDefaults {

    @Composable
    fun iconToggleButtonColors() = IconButtonDefaults.iconToggleButtonColors()
}

@Composable
@ThemePreviews
fun ScIconButtonPreview() {
    ScTheme {
        ScIconToggleButton(
            checked = true,
            onCheckedChange = { },
            icon = ScIcons.FavoriteBorder,
            checkedIcon = ScIcons.Favorite,
            contentDescription = null,
        )
    }
}

@Composable
@ThemePreviews
fun ScIconButtonPreviewUnchecked() {
    ScTheme {
        ScIconToggleButton(
            checked = false,
            onCheckedChange = { },
            icon = ScIcons.FavoriteBorder,
            checkedIcon = ScIcons.Favorite,
            contentDescription = null,
        )
    }
}