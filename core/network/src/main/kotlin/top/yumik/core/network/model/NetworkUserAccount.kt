package top.yumik.core.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkUserAccount(
    @Json(name = "profile")
    val profile: Profile
) {
    @JsonClass(generateAdapter = true)
    data class Profile(
        @Json(name = "userId")
        val userId: Int
    )
}