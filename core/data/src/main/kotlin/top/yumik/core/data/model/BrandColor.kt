package top.yumik.core.data.model

import top.yumik.core.data.R
import top.yumik.core.designsystem.color.ScColors
import top.yumik.core.model.BrandColor

val BrandColor.color
    get() = when (this) {
        BrandColor.DEFAULT -> ScColors.DEFAULT
        BrandColor.BLUE -> ScColors.BLUE
        BrandColor.RED -> ScColors.RED
        BrandColor.GREEN -> ScColors.GREEN
        BrandColor.YELLOW -> ScColors.YELLOW
    }

val BrandColor.colorNameRes
    get() = when (this) {
        BrandColor.DEFAULT -> R.string.color_default
        BrandColor.BLUE -> R.string.color_blue
        BrandColor.RED -> R.string.color_red
        BrandColor.GREEN -> R.string.color_green
        BrandColor.YELLOW -> R.string.color_yellow
    }
