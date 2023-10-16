package top.yumik.core.network.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.converter.moshi.MoshiConverterFactory
import top.yumik.core.network.BuildConfig
import top.yumik.core.network.adapter.NetworkLoginQrCheckCodeAdapter
import top.yumik.core.network.adapter.NetworkUserPlaylistAdapter
import top.yumik.core.network.interceptor.TimestampInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .add(NetworkLoginQrCheckCodeAdapter())
            .add(NetworkUserPlaylistAdapter())
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun providesMoshiConverterFactory(
        moshi: Moshi
    ): Converter.Factory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    @Singleton
    fun providesOkHttpCallFactory(
        timestampInterceptor: TimestampInterceptor
    ): Call.Factory {
        return OkHttpClient.Builder()
            .addInterceptor(timestampInterceptor)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    if (BuildConfig.DEBUG) {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                }
            )
            .build()
    }
}