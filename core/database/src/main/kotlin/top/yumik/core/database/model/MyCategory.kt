package top.yumik.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_category")
data class MyCategory(
    @PrimaryKey(autoGenerate = true)
    val uid: Long = 0,
    val userId: Int,

    val id: Long,
    val createdUserId: Int,
    val coverImgUrl: String,
    val description: String?,
    val name: String,
    val playCount: Int,
    val tags: List<String>,
    val trackCount: Int,
    val specialType: SpecialType
) {
    enum class SpecialType {
        FAVORITE, USER_CREATED, UNKNOWN
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MyCategory) return false

        if (userId != other.userId) return false
        if (id != other.id) return false
        if (createdUserId != other.createdUserId) return false
        if (coverImgUrl != other.coverImgUrl) return false
        if (description != other.description) return false
        if (name != other.name) return false
        if (playCount != other.playCount) return false
        if (tags != other.tags) return false
        if (trackCount != other.trackCount) return false
        if (specialType != other.specialType) return false

        return true
    }

    override fun hashCode(): Int {
        var result = userId
        result = 31 * result + id.hashCode()
        result = 31 * result + createdUserId.hashCode()
        result = 31 * result + coverImgUrl.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + name.hashCode()
        result = 31 * result + playCount
        result = 31 * result + tags.hashCode()
        result = 31 * result + trackCount
        result = 31 * result + specialType.hashCode()
        return result
    }

}
