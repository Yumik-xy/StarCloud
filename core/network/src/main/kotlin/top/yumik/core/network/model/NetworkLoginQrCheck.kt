package top.yumik.core.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkLoginQrCheck(
    @Json(name = "code")
    val code: Code,
    @Json(name = "cookie")
    val cookie: String
) {
    enum class Code(val code: Int) {
        /** 二维码过期 */
        @Json(name = "800")
        EXPIRED(800),

        /** 二维码未扫描 */
        @Json(name = "801")
        NOT_SCAN(801),

        /** 等待确认 */
        @Json(name = "802")
        WAIT_CONFIRM(802),

        /** 授权登录 */
        @Json(name = "803")
        CONFIRMED(803);

        companion object {
            fun from(code: Int): Code {
                return values().firstOrNull { it.code == code }
                    ?: throw IllegalArgumentException("Unknown code: $code")
            }
        }
    }
}