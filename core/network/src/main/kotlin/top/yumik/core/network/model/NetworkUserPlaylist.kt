package top.yumik.core.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class NetworkUserPlaylist(
    @Json(name = "more")
    val more: Boolean,
    @Json(name = "playlist")
    val playlist: List<PlayInfo>
) {
    @JsonClass(generateAdapter = true)
    data class PlayInfo(
        @Json(name = "id")
        val id: Long,
        @Json(name = "userId")
        val createdUserId: Int,
        @Json(name = "coverImgUrl")
        val coverImgUrl: String,
        @Json(name = "description")
        val description: String?,
        @Json(name = "name")
        val name: String,
        @Json(name = "playCount")
        val playCount: Int,
        @Json(name = "tags")
        val tags: List<String>,
        @Json(name = "trackCount")
        val trackCount: Int,
        @Json(name = "specialType")
        val specialType: SpecialType
    ) {
        enum class SpecialType(val type: Int) {
            FAVORITE(5),
            USER_CREATED(0),
            UNKNOWN(-1);

            companion object {
                fun from(type: Int): SpecialType {
                    return values().firstOrNull { it.type == type } ?: UNKNOWN
                }
            }
        }
    }
}