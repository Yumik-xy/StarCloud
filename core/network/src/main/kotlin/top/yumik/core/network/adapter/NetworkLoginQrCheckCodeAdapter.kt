package top.yumik.core.network.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import top.yumik.core.network.model.NetworkLoginQrCheck

class NetworkLoginQrCheckCodeAdapter {

    @ToJson
    fun toJson(enum: NetworkLoginQrCheck.Code): Int {
        return enum.code
    }

    @FromJson
    fun fromJson(code: Int): NetworkLoginQrCheck.Code {
        return NetworkLoginQrCheck.Code.from(code)
    }
}