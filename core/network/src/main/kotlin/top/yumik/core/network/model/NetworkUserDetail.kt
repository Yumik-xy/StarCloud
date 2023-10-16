package top.yumik.core.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkUserDetail(
    @Json(name = "level")
    val level: Int,
    @Json(name = "profile")
    val profile: Profile
) {

    @JsonClass(generateAdapter = true)
    data class Profile(
        @Json(name = "userId")
        val userId: Int,
        @Json(name = "nickname")
        val nickname: String,
        @Json(name = "avatarUrl")
        val avatarUrl: String,
        @Json(name = "vipType")
        val vipType: Int,
        @Json(name = "followeds")
        val fans: Int,
        @Json(name = "follows")
        val follows: Int
    )
}
