package top.yumik.core.data.repository

import kotlinx.coroutines.flow.Flow
import top.yumik.core.data.util.Syncable
import top.yumik.core.database.model.MyCategory

interface MyCategoryRepository : Syncable {

    val myCategories: Flow<List<MyCategory>>
}