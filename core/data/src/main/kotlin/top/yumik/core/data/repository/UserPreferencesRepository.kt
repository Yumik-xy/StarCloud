package top.yumik.core.data.repository

import kotlinx.coroutines.flow.Flow
import top.yumik.core.model.BrandColor
import top.yumik.core.model.DarkTheme
import top.yumik.core.model.UserPreferences

interface UserPreferencesRepository {

    val userPreferences: Flow<UserPreferences>

    suspend fun setBrandColor(brandColor: BrandColor)

    suspend fun setDarkTheme(darkTheme: DarkTheme)

}