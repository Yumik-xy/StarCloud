package top.yumik.feature.login

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking
import top.yumik.core.common.canceljob.cancelableJob
import top.yumik.core.common.qrcode.QRCode
import top.yumik.core.common.result.Result
import top.yumik.core.data.util.NetworkMonitor
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    networkMonitor: NetworkMonitor
) : ViewModel() {

    private val loginState: MutableStateFlow<Result<Bitmap>> = MutableStateFlow(Result.Loading)

    val state = combine(loginState, networkMonitor.isOnline) { loginState, isOnlineState ->
        LoginState(
            isOffline = !isOnlineState,
            bitmap = loginState
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = LoginState()
    )

    fun refreshLoginBitmap() {
        viewModelScope.cancelableJob("refresh-login-bitmap") {
            loginState.update { Result.Loading }
            delay(2000L)
            loginState.update { Result.Success(runBlocking { QRCode.generate("Test") }) }
        }
    }
}

data class LoginState(
    val isOffline: Boolean = false,
    val bitmap: Result<Bitmap> = Result.Loading
)