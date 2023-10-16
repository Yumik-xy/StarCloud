package top.yumik.core.network.retorfit

import okhttp3.Call
import retrofit2.Converter
import retrofit2.Retrofit
import top.yumik.core.network.BuildConfig
import top.yumik.core.network.model.NetworkLoginQrCheck
import top.yumik.core.network.model.NetworkUserDetail
import top.yumik.core.network.model.NetworkUserPlaylist
import javax.inject.Inject
import javax.inject.Singleton

private const val SC_BASE_URL = BuildConfig.BASE_URL

@Singleton
class RetrofitScNetwork @Inject constructor(
    modelConverterFactory: Converter.Factory,
    okhttpCallFactory: Call.Factory
) : ScNetworkDataSource {
    private val retrofit = Retrofit.Builder()
        .baseUrl(SC_BASE_URL)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(modelConverterFactory)
        .build()

    private val loginApi = retrofit.create(LoginApi::class.java)
    private val userDetailApi = retrofit.create(UserDetailApi::class.java)
    private val playlistApi = retrofit.create(PlaylistApi::class.java)

    override suspend fun fetchQrKey(): String {
        return loginApi.fetchQrKey().data.key
    }

    override suspend fun fetchQrAuthUrl(key: String): String {
        return loginApi.fetchQrAuthUrl(key).data.authUrl
    }

    override suspend fun checkQrAuthState(key: String): NetworkLoginQrCheck {
        return loginApi.checkAuthState(key)
    }

    override suspend fun fetchMyUserDetail(cookie: String): NetworkUserDetail {
        val userId = userDetailApi.fetchUserAccount(cookie).profile.userId
        return fetchUserDetail(cookie, userId)
    }

    override suspend fun fetchUserDetail(cookie: String, userId: Int): NetworkUserDetail {
        return userDetailApi.fetchUserDetail(cookie, userId)
    }

    override suspend fun fetchCategoryList(userId: Int): NetworkUserPlaylist {
        return playlistApi.fetchUserPlaylist(userId)
    }
}