package top.yumik.core.common.qrcode

import android.graphics.Bitmap
import android.graphics.Color
import androidx.collection.LruCache
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.EnumMap

object QRCode {

    // 生存图片的缓存，采取LRU算法
    private val cacheBitmap = LruCache<String, Bitmap>(10)

    suspend fun generate(key: String): Bitmap = withContext(Dispatchers.IO) {

        // 从缓存中获取图片
        var bitmap = cacheBitmap.get(key)
        if (bitmap != null) {
            return@withContext bitmap
        }

        val size = 512

        // 生成图片
        val hints: MutableMap<EncodeHintType, Any> = EnumMap(EncodeHintType::class.java)
        hints[EncodeHintType.CHARACTER_SET] = "utf-8"
        hints[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.H
        hints[EncodeHintType.MARGIN] = 0 // 设置二维码边的边距，0表示无边距

        val bitMatrix = QRCodeWriter().encode(key, BarcodeFormat.QR_CODE, size, size, hints)

        bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        for (y in 0 until size) for (x in 0 until size) {
            bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.TRANSPARENT)
        }
        cacheBitmap.put(key, bitmap)
        return@withContext bitmap
    }
}