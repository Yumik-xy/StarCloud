package top.yumik.core.data.util

interface Syncable {

    suspend fun sync(): Boolean
}