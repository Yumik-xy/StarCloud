package top.yumik.core.designsystem.preview

import androidx.compose.runtime.Composable
import top.yumik.core.designsystem.component.ScBackground
import top.yumik.core.designsystem.theme.ScTheme

@Composable
fun ScPreview(
    content: @Composable () -> Unit
) {
    ScTheme {
        ScBackground {
            content()
        }
    }
}