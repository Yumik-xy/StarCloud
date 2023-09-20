package top.yumik.core.designsystem.color

import androidx.annotation.StringRes
import androidx.compose.material3.ColorScheme
import top.yumik.core.designsystem.R

enum class ScColors(
    @StringRes val colorName: Int,
    val lightColorScheme: ColorScheme,
    val darkColorScheme: ColorScheme
) {
    DEFAULT(R.string.color_default, DefaultLightColors, DefaultDarkColors),
    BLUE(R.string.color_blue, BlueLightColors, BlueDarkColors)
}