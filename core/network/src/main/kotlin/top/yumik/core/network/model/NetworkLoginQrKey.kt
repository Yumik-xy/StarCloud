package top.yumik.core.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkLoginQrKey(
    @Json(name = "data")
    val data: Data
) {

    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "unikey")
        val key: String = ""
    )
}