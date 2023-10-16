package top.yumik.core.data.model

import top.yumik.core.data.R
import top.yumik.core.model.DarkTheme

val DarkTheme.darkThemeNameRes
    get() = when (this) {
        DarkTheme.FOLLOW_SYSTEM -> R.string.dark_theme_follow_system
        DarkTheme.LIGHT -> R.string.dark_theme_light
        DarkTheme.DARK -> R.string.dark_theme_dark
    }