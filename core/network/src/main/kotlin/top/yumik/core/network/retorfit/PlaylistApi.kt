package top.yumik.core.network.retorfit

import retrofit2.http.POST
import retrofit2.http.Query
import top.yumik.core.network.model.NetworkUserPlaylist

interface PlaylistApi {

    /**
     * 调用接口，获取指定用户的歌单
     */
    @POST("user/playlist")
    suspend fun fetchUserPlaylist(
        @Query("uid") uid: Int,
        @Query("limit") limit: Int = 30,
        @Query("offset") offset: Int = 0
    ): NetworkUserPlaylist
}