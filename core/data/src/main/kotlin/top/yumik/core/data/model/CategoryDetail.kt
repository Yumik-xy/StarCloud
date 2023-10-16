package top.yumik.core.data.model

import top.yumik.core.database.model.MyCategory
import top.yumik.core.network.model.NetworkUserPlaylist

fun NetworkUserPlaylist.PlayInfo.getModelWithUserId(userId: Int) = MyCategory(
    userId = userId,
    id = id,
    createdUserId = createdUserId,
    coverImgUrl = coverImgUrl,
    description = description,
    name = name,
    playCount = playCount,
    tags = tags,
    trackCount = trackCount,
    specialType = when (specialType) {
        NetworkUserPlaylist.PlayInfo.SpecialType.FAVORITE -> MyCategory.SpecialType.FAVORITE
        NetworkUserPlaylist.PlayInfo.SpecialType.USER_CREATED -> MyCategory.SpecialType.USER_CREATED
        NetworkUserPlaylist.PlayInfo.SpecialType.UNKNOWN -> MyCategory.SpecialType.UNKNOWN
    }
)