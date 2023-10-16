package top.yumik.core.designsystem.color

import androidx.compose.material3.ColorScheme

enum class ScColors(
    val lightColorScheme: ColorScheme,
    val darkColorScheme: ColorScheme
) {
    DEFAULT(DefaultLightColors, DefaultDarkColors),
    BLUE(BlueLightColors, BlueDarkColors),
    RED(RedLightColors, RedDarkColors),
    GREEN(GreenLightColors, GreenDarkColors),
    YELLOW(YellowLightColors, YellowDarkColors),
}