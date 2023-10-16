package top.yumik.core.domain

import android.graphics.Bitmap
import top.yumik.core.common.qrcode.QRCode
import top.yumik.core.network.retorfit.RetrofitScNetwork
import javax.inject.Inject

class FetchQrAuthBitmapUseCase @Inject constructor(
    private val networkDataSource: RetrofitScNetwork
) {

    suspend operator fun invoke(key: String): Bitmap {
        val authUrl = networkDataSource.fetchQrAuthUrl(key)
        return QRCode.generate(authUrl)
    }
}