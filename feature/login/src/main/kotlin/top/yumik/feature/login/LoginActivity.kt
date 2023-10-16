package top.yumik.feature.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import top.yumik.core.common.intent.startActivity
import top.yumik.core.designsystem.preview.ScPreview

private const val TAG = "LoginActivity"

fun navigateToLogin() = startActivity<LoginActivity>()

@AndroidEntryPoint
internal class LoginActivity : ComponentActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var uiState: LoginState by mutableStateOf(LoginState())

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state
                    .onEach {
                        uiState = it
                    }
                    .collect()
            }
        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.event.collect {
                    when (it) {
                        LoginEvent.Authenticated -> {
                            finish()
                        }
                    }
                }
            }
        }

        enableEdgeToEdge()

        setContent {
            ScPreview {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen(
                        uiState = uiState,
                        onRefreshQRCodeBitmap = viewModel::refreshLoginBitmap,
                        onNavigationClick = { finish() }
                    )
                }
            }
        }
    }
}