package top.yumik.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import top.yumik.core.database.model.MyCategory

@Dao
interface MyCategoriesDao {

    @Transaction
    @Query("SELECT * FROM my_category")
    fun getMyCategories(): Flow<List<MyCategory>>

    @Upsert
    suspend fun upsertMyCategories(myCategories: List<MyCategory>)

    @Delete
    suspend fun deleteMyCategories(myCategories: List<MyCategory>)
}