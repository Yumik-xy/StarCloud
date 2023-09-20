package top.yumik.core.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun ScSelectedIcon(
    isSelected: Boolean,
    unselectedIcon: ImageVector,
    selectedIcon: ImageVector = unselectedIcon,
    contentDescription: String?,
) {
    AnimatedVisibility(
        visible = isSelected,
        enter = fadeIn() + expandIn(),
        exit = fadeOut() + shrinkOut()
    ) {
        Icon(
            imageVector = selectedIcon,
            contentDescription = contentDescription
        )
    }
    AnimatedVisibility(
        visible = !isSelected,
        enter = fadeIn() + expandIn(),
        exit = fadeOut() + shrinkOut()
    ) {
        Icon(
            imageVector = unselectedIcon,
            contentDescription = contentDescription
        )
    }
}