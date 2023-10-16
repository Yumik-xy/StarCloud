package top.yumik.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import top.yumik.core.database.converter.MyCategorySpecialTypeConverter
import top.yumik.core.database.converter.StringListConverter
import top.yumik.core.database.dao.MyCategoriesDao
import top.yumik.core.database.model.MyCategory

@Database(
    entities = [
        MyCategory::class
    ],
    version = 1
)
@TypeConverters(
    MyCategorySpecialTypeConverter::class,
    StringListConverter::class
)
abstract class ScDatabase : RoomDatabase() {

    abstract fun myCategoriesDao(): MyCategoriesDao
}