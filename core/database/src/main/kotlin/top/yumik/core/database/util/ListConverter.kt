package top.yumik.core.database.util

internal inline fun <reified T> List<T>.toJson() = getListAdapter<T>().toJson(this) ?: ""

internal inline fun <reified T> String.fromJson() = getListAdapter<T>().fromJson(this).orEmpty()