package top.yumik.core.data.repository

import kotlinx.coroutines.flow.Flow
import top.yumik.core.datastore.UserPreferencesDataSource
import top.yumik.core.model.BrandColor
import top.yumik.core.model.DarkTheme
import top.yumik.core.model.UserPreferences
import javax.inject.Inject

class OfflineFirstUserPreferencesRepository @Inject constructor(
    private val userPreferencesDataSource: UserPreferencesDataSource
) : UserPreferencesRepository {

    override val userPreferences: Flow<UserPreferences> = userPreferencesDataSource.userPreferences

    override suspend fun setBrandColor(brandColor: BrandColor) {
        userPreferencesDataSource.setBrandColor(brandColor)
    }

    override suspend fun setDarkTheme(darkTheme: DarkTheme) {
        userPreferencesDataSource.setDarkTheme(darkTheme)
    }

}