import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import top.yumik.core.designsystem.preview.ScPreview
import top.yumik.core.designsystem.preview.ThemePreviews

@Composable
fun <T> VerticalGrid(
    modifier: Modifier = Modifier,
    items: Array<out T>,
    minItemWidth: Dp = Dp.Unspecified,
    itemHorizontalSpacing: Dp = 0.dp,
    itemVerticalSpacing: Dp = 0.dp,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    content: @Composable (item: T) -> Unit
) {
    BoxWithConstraints(
        modifier = modifier.padding(contentPadding)
    ) {
        val horizontalPadding = 0.dp
        val columns = if (minItemWidth == Dp.Unspecified) {
            1
        } else {
            ((maxWidth - horizontalPadding) / minItemWidth).toInt().coerceAtLeast(1)
        }
        val rows = (items.size + columns - 1) / columns
        val itemWidth = (maxWidth - horizontalPadding - itemHorizontalSpacing * (columns - 1)) /
                columns
        Column(
            verticalArrangement = Arrangement.spacedBy(itemVerticalSpacing)
        ) {
            for (rowIndex in 0 until rows) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(itemHorizontalSpacing)
                ) {
                    for (columnIndex in 0 until columns) {
                        val index = rowIndex * columns + columnIndex
                        if (index >= items.size) {
                            break
                        }
                        Box(modifier = Modifier.width(itemWidth)) {
                            content(items[index])
                        }
                    }
                }
            }
        }
    }
}

@Composable
@ThemePreviews
fun VerticalGridPreview() {
    ScPreview {
        VerticalGrid(
            items = Array(10) { it },
            minItemWidth = 100.dp,
            contentPadding = PaddingValues(16.dp),
            itemHorizontalSpacing = 8.dp,
            itemVerticalSpacing = 8.dp
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = it.toString(),
                textAlign = TextAlign.Center
            )
        }
    }
}