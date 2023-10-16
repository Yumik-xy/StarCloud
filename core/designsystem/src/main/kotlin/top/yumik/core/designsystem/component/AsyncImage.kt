package top.yumik.core.designsystem.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter.State.Error
import coil.compose.AsyncImagePainter.State.Loading
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import top.yumik.core.designsystem.R
import top.yumik.core.designsystem.theme.LocalTintTheme

private const val TAG = "AsyncImage"

@Composable
fun ScAsyncImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentDescription: String?,
    placeholder: Painter = painterResource(R.drawable.ic_placeholder_default),
) {
    val iconTint = LocalTintTheme.current.iconTint
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }
    val imageLoader = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .build(),
        onState = { state ->
            isLoading = state is Loading
            isError = state is Error
        }
    )
    val isLocalInspection = LocalInspectionMode.current
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        if (isLoading && !isLocalInspection) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.Center),
                color = MaterialTheme.colorScheme.tertiary,
            )
        }
        Image(
            modifier = Modifier.clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
            painter = if (isError.not() && !isLocalInspection) imageLoader else placeholder,
            contentDescription = contentDescription,
            colorFilter = if (iconTint != null) ColorFilter.tint(iconTint) else null
        )
    }
}