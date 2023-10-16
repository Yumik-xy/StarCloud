package top.yumik.core.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun <T> ScSegmentedRow(
    modifier: Modifier = Modifier,
    items: Array<out T>,
    selectedItem: T,
    onSelected: (T) -> Unit,
    layout: SegmentedButtonLayout = ScSegmentedButtonDefaults.layout,
    shapePercent: Int = ScSegmentedButtonDefaults.shapePercent,
    border: SegmentedButtonBorder = ScSegmentedButtonDefaults.border(),
    colors: SegmentedButtonColors = ScSegmentedButtonDefaults.colors(),
    itemContent: @Composable RowScope.(T) -> Unit
) {
    Row(modifier = modifier) {
        items.forEachIndexed { index, item ->
            ScSegmentedButton(
                modifier = when (layout) {
                    SegmentedButtonLayout.WARP_CONTENT -> Modifier
                        .wrapContentSize()
                        .offset(x = (-1 * index).dp, y = 0.dp)
                        .zIndex(if (selectedItem == item) 1F else 0F)

                    SegmentedButtonLayout.AVERAGE -> Modifier
                        .weight(1F)
                        .offset(x = (-1 * index).dp, y = 0.dp)
                        .zIndex(if (selectedItem == item) 1F else 0F)
                },
                onClick = {
                    onSelected(item)
                },
                shape = when (index) {
                    0 -> RoundedCornerShape(
                        topStartPercent = shapePercent,
                        topEndPercent = 0,
                        bottomStartPercent = shapePercent,
                        bottomEndPercent = 0
                    )

                    items.size - 1 -> RoundedCornerShape(
                        topStartPercent = 0,
                        topEndPercent = shapePercent,
                        bottomStartPercent = 0,
                        bottomEndPercent = shapePercent
                    )

                    else -> RoundedCornerShape(
                        topStartPercent = 0,
                        topEndPercent = 0,
                        bottomStartPercent = 0,
                        bottomEndPercent = 0
                    )
                },
                contentPadding = PaddingValues(
                    start = if (index == 0) 16.dp else 12.dp,
                    top = 4.dp,
                    end = if (index == items.size - 1) 16.dp else 12.dp,
                    bottom = 4.dp
                ),
                border = BorderStroke(
                    width = border.width,
                    color = if (selectedItem == item) border.color else border.unselectedColor
                ),
                containerColor = if (selectedItem == item) colors.containerColor else colors.unselectedContainerColor,
                contentColor = if (selectedItem == item) colors.contentColor else colors.unselectedContentColor
            ) {
                CompositionLocalProvider(LocalTextStyle provides MaterialTheme.typography.labelMedium) {
                    itemContent(item)
                }
            }
        }
    }
}

@Composable
private fun ScSegmentedButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    shape: RoundedCornerShape,
    contentPadding: PaddingValues,
    border: BorderStroke,
    containerColor: Color,
    contentColor: Color,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .clip(shape)
            .border(border, shape)
            .background(containerColor)
            .clickable(onClick = onClick)
            .padding(contentPadding),
        contentAlignment = Alignment.Center
    ) {
        CompositionLocalProvider(LocalContentColor provides contentColor) {
            content()
        }
    }
}

enum class SegmentedButtonLayout {
    WARP_CONTENT, AVERAGE

    // TODO: 增加固定大小 Fixed，可能需要换用 sealed class
}

data class SegmentedButtonBorder(
    val width: Dp,
    val color: Color,
    val unselectedColor: Color,
)

data class SegmentedButtonColors(
    val containerColor: Color,
    val unselectedContainerColor: Color,
    val contentColor: Color,
    val unselectedContentColor: Color
)

object ScSegmentedButtonDefaults {

    const val shapePercent = 50

    val layout = SegmentedButtonLayout.WARP_CONTENT

    @Composable
    fun border(
        width: Dp = 1.dp,
        color: Color = MaterialTheme.colorScheme.outline,
        unselectedColor: Color = color.copy(alpha = 0.83f),
    ): SegmentedButtonBorder {
        return SegmentedButtonBorder(
            width = width,
            color = color,
            unselectedColor = unselectedColor
        )
    }

    @Composable
    fun colors(
        containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
        unselectedContainerColor: Color = Color.Transparent,
        contentColor: Color = MaterialTheme.colorScheme.contentColorFor(containerColor),
        unselectedContentColor: Color = contentColor
    ): SegmentedButtonColors {
        return SegmentedButtonColors(
            containerColor = containerColor,
            unselectedContainerColor = unselectedContainerColor,
            contentColor = contentColor,
            unselectedContentColor = unselectedContentColor
        )
    }
}