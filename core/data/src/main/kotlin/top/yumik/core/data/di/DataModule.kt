package top.yumik.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import top.yumik.core.data.repository.MyCategoryRepository
import top.yumik.core.data.repository.OfflineFirstMyCategoryRepository
import top.yumik.core.data.repository.OfflineFirstUserDetailRepository
import top.yumik.core.data.repository.OfflineFirstUserPreferencesRepository
import top.yumik.core.data.repository.UserDetailRepository
import top.yumik.core.data.repository.UserPreferencesRepository
import top.yumik.core.data.util.ConnectivityManagerNetworkMonitor
import top.yumik.core.data.util.NetworkMonitor

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor

    @Binds
    fun bindsUserPreferences(
        userPreferences: OfflineFirstUserPreferencesRepository,
    ): UserPreferencesRepository

    @Binds
    fun bindsUserDetailRepository(
        userDetailRepository: OfflineFirstUserDetailRepository,
    ): UserDetailRepository

    @Binds
    fun bindsMyCategoryRepository(
        myCategoryRepository: OfflineFirstMyCategoryRepository,
    ): MyCategoryRepository
}