package top.yumik.core.network.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import top.yumik.core.network.model.NetworkUserPlaylist

class NetworkUserPlaylistAdapter {

    @ToJson
    fun toJson(type: NetworkUserPlaylist.PlayInfo.SpecialType): Int {
        return type.type
    }

    @FromJson
    fun fromJson(type: Int): NetworkUserPlaylist.PlayInfo.SpecialType {
        return NetworkUserPlaylist.PlayInfo.SpecialType.from(type)
    }
}