package top.yumik.core.datastore

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.map
import top.yumik.core.model.BrandColor
import top.yumik.core.model.DarkTheme
import top.yumik.core.model.UserPreferences
import javax.inject.Inject

class UserPreferencesDataSource @Inject constructor(
    private val userPreferencesDataStore: DataStore<UserPreferencesProto>
) {

    val userPreferences = userPreferencesDataStore.data
        .map {
            UserPreferences(
                brandColor = when (it.brandColor) {
                    null,
                    BrandColorProto.UNRECOGNIZED,
                    BrandColorProto.BRAND_COLOR_UNSPECIFIED,
                    BrandColorProto.BRAND_COLOR_DEFAULT -> BrandColor.DEFAULT

                    BrandColorProto.BRAND_COLOR_BLUE -> BrandColor.BLUE
                    BrandColorProto.BRAND_COLOR_RED -> BrandColor.RED
                    BrandColorProto.BRAND_COLOR_GREEN -> BrandColor.GREEN
                    BrandColorProto.BRAND_COLOR_YELLOW -> BrandColor.YELLOW
                },
                darkTheme = when (it.darkTheme) {
                    null,
                    DarkThemeProto.UNRECOGNIZED,
                    DarkThemeProto.DARK_THEME_UNSPECIFIED,
                    DarkThemeProto.DARK_THEME_FOLLOW_SYSTEM -> DarkTheme.FOLLOW_SYSTEM

                    DarkThemeProto.DARK_THEME_LIGHT -> DarkTheme.LIGHT
                    DarkThemeProto.DARK_THEME_DARK -> DarkTheme.DARK
                }
            )
        }

    suspend fun setBrandColor(brandColor: BrandColor) {
        userPreferencesDataStore.updateData {
            it.copy {
                this.brandColor = when (brandColor) {
                    BrandColor.DEFAULT -> BrandColorProto.BRAND_COLOR_DEFAULT
                    BrandColor.BLUE -> BrandColorProto.BRAND_COLOR_BLUE
                    BrandColor.RED -> BrandColorProto.BRAND_COLOR_RED
                    BrandColor.GREEN -> BrandColorProto.BRAND_COLOR_GREEN
                    BrandColor.YELLOW -> BrandColorProto.BRAND_COLOR_YELLOW
                }
            }
        }
    }

    suspend fun setDarkTheme(darkTheme: DarkTheme) {
        userPreferencesDataStore.updateData {
            it.copy {
                this.darkTheme = when (darkTheme) {
                    DarkTheme.FOLLOW_SYSTEM -> DarkThemeProto.DARK_THEME_FOLLOW_SYSTEM
                    DarkTheme.LIGHT -> DarkThemeProto.DARK_THEME_LIGHT
                    DarkTheme.DARK -> DarkThemeProto.DARK_THEME_DARK
                }
            }
        }
    }
}