package top.yumik.core.database.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import top.yumik.core.database.ScDatabase
import top.yumik.core.database.dao.MyCategoriesDao

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    fun providesMyCategoriesDao(
        database: ScDatabase
    ): MyCategoriesDao = database.myCategoriesDao()
}