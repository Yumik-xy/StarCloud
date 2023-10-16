package top.yumik.core.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import top.yumik.core.data.model.getModelWithUserId
import top.yumik.core.data.util.compare
import top.yumik.core.database.dao.MyCategoriesDao
import top.yumik.core.database.model.MyCategory
import top.yumik.core.network.retorfit.RetrofitScNetwork
import javax.inject.Inject

class OfflineFirstMyCategoryRepository @Inject constructor(
    private val userDetailRepository: UserDetailRepository,
    private val networkDataSource: RetrofitScNetwork,
    private val myCategoriesDao: MyCategoriesDao
) : MyCategoryRepository {

    override val myCategories: Flow<List<MyCategory>> = myCategoriesDao.getMyCategories()

    override suspend fun sync(): Boolean = kotlin.runCatching {

        val userDetail = userDetailRepository.userDetail.first()
        val newList = networkDataSource
            .fetchCategoryList(userDetail.userId)
            .playlist
            .map { it.getModelWithUserId(userDetail.userId) }
        val oldList = myCategoriesDao.getMyCategories().first()

        compare(
            oldList = oldList,
            newList = newList,
            isItemSame = { oldItem, newItem ->
                oldItem.id == newItem.id
            },
            isContentSame = { oldItem, newItem ->
                oldItem == newItem
            },
            onUpsert = myCategoriesDao::upsertMyCategories,
            onDelete = myCategoriesDao::deleteMyCategories
        )

    }.isSuccess
}