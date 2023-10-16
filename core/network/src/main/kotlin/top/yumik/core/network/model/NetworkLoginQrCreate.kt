package top.yumik.core.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkLoginQrCreate(
    @Json(name = "data")
    val data: Data
) {

    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "qrurl")
        val authUrl: String = ""
    )
}