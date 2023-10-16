package top.yumik.core.data.fake

import top.yumik.core.database.model.MyCategory

val fakeMyCategory = MyCategory(
    userId = 1,
    id = 1,
    createdUserId = 1,
    coverImgUrl = "https://img1.baidu.com/it/u=4060770951,4069855872&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
    description = "歌单描述",
    name = "歌单名称",
    playCount = 100,
    tags = listOf("标签1", "标签2"),
    trackCount = 10,
    specialType = MyCategory.SpecialType.USER_CREATED
)


val fakeCategories = listOf(
    MyCategory(
        userId = 1,
        id = 1,
        createdUserId = 1,
        coverImgUrl = "https://img1.baidu.com/it/u=4060770951,4069855872&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
        description = "歌单描述",
        name = "歌单名称",
        playCount = 100,
        tags = listOf("标签1", "标签2"),
        trackCount = 10,
        specialType = MyCategory.SpecialType.USER_CREATED
    ),
    MyCategory(
        userId = 1,
        id = 2,
        createdUserId = 1,
        coverImgUrl = "https://img1.baidu.com/it/u=4060770951,4069855872&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
        description = "歌单描述",
        name = "歌单名称",
        playCount = 100,
        tags = listOf("标签1", "标签2"),
        trackCount = 10,
        specialType = MyCategory.SpecialType.USER_CREATED
    ),
    MyCategory(
        userId = 1,
        id = 3,
        createdUserId = 2,
        coverImgUrl = "https://img1.baidu.com/it/u=4060770951,4069855872&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
        description = "歌单描述",
        name = "歌单名称",
        playCount = 100,
        tags = listOf("标签1", "标签2"),
        trackCount = 10,
        specialType = MyCategory.SpecialType.USER_CREATED
    )
)