@file:Suppress("NOTHING_TO_INLINE")

package top.yumik.core.database.util

internal inline fun <T : Enum<T>> T.toInt() = this.ordinal

internal inline fun <reified T : Enum<T>> Int.toEnum() = enumValues<T>()[this]