package top.yumik.core.database.converter

import androidx.room.TypeConverter
import top.yumik.core.database.util.fromJson
import top.yumik.core.database.util.toJson

class StringListConverter {

    @TypeConverter
    fun stringListToJson(value: List<String>): String = value.toJson()

    @TypeConverter
    fun jsonToStringList(value: String): List<String> = value.fromJson()
}