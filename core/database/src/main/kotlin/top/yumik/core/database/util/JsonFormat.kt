package top.yumik.core.database.util

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

private val moshi: Moshi by lazy {
    Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
}

internal fun <T> getAdapter(clazz: Class<T>): JsonAdapter<T> = moshi.adapter(clazz)

internal inline fun <reified T> getAdapter() = getAdapter(T::class.java)

internal fun <T> getListAdapter(clazz: Class<T>): JsonAdapter<List<T>> =
    moshi.adapter(Types.newParameterizedType(List::class.java, clazz))

internal inline fun <reified T> getListAdapter() = getListAdapter(T::class.java)