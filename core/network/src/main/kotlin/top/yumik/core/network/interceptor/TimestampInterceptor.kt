package top.yumik.core.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation
import javax.inject.Inject

private const val QUERY_TIMESTAMP = "timestamp"

class TimestampInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val builder = originalRequest.newBuilder()

        if (originalRequest
                .tag(Invocation::class.java)
                ?.method()
                ?.getAnnotation(Timestamp::class.java) != null
        ) {
            val httpUrl = originalRequest.url.newBuilder()
                .addQueryParameter(QUERY_TIMESTAMP, System.currentTimeMillis().toString())
                .build()
            builder.url(httpUrl)
        }

        return chain.proceed(builder.build())
    }
}

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class Timestamp

