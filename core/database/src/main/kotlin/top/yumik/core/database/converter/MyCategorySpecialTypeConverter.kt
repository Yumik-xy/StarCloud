package top.yumik.core.database.converter

import androidx.room.TypeConverter
import top.yumik.core.database.model.MyCategory
import top.yumik.core.database.util.toEnum
import top.yumik.core.database.util.toInt

class MyCategorySpecialTypeConverter {

    @TypeConverter
    fun myCategorySpecialTypeToInt(value: MyCategory.SpecialType): Int = value.toInt()

    @TypeConverter
    fun intToMyCategorySpecialType(value: Int): MyCategory.SpecialType = value.toEnum()
}