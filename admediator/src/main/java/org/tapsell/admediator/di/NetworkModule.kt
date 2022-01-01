package org.tapsell.admediator.di

import com.google.gson.Gson
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.ConnectionPool
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.tapsell.admediator.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


object NetworkModule {

    /**
     * Provides base url for [provideRetrofit] method
     */
    fun provideBaseUrl() = BuildConfig.API_BASE_URL

    /**
     * Provides log level for [provideHttpLoggingInterceptor] method
     */
    fun provideLogLevel() =
        if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE

    /**
     * Provides HttpLoggingInterceptor to use in [provideOkHttpClient] method
     */
    fun provideHttpLoggingInterceptor(
        logLevel: HttpLoggingInterceptor.Level
    ) = HttpLoggingInterceptor().apply { level = logLevel }

    /**
     * Provides HeadersInterceptor to use in [provideOkHttpClient] method
     */
    fun provideHeadersInterceptor() = Interceptor { chain ->
        chain.proceed(
            chain.request().newBuilder()
//                .addHeader("Authorization", "")
                .addHeader("Content-Type", "application/json")
                .addHeader("accept", "application/json")
                .build()
        )
    }


    /**
     * Provides OkHttpClient to use in [provideRetrofit] method
     */
    fun provideOkHttpClient(
        interceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor,
    ) = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(interceptor)
        .connectionPool(ConnectionPool(10, 2, TimeUnit.MINUTES))
        .dispatcher(
            Dispatcher().apply {
                // Allow for high number of concurrent image fetches on same host.
                maxRequestsPerHost = 15
            }
        )
        .connectTimeout(30L, TimeUnit.SECONDS)
        .readTimeout(30L, TimeUnit.SECONDS)
        .build()

    /**
     * Provides Moshi to use in [provideMoshiConverterFactory] method
     */
    fun provideMoshiBuilder(): Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    /**
     * Provides MoshiConverterFactory to use in [provideRetrofit] method
     */
    fun provideMoshiConverterFactory(
        moshi: Moshi
    ): MoshiConverterFactory = MoshiConverterFactory.create(moshi)

    /**
     * provides Retrofit to use in [ApiServiceModule]
     */
    fun provideRetrofit(
        moshiConverterFactory: MoshiConverterFactory = provideMoshiConverterFactory(
            provideMoshiBuilder()
        ),
        okHttpClient: OkHttpClient = provideOkHttpClient(
            provideHeadersInterceptor(),
            provideHttpLoggingInterceptor(provideLogLevel())
        ),
        baseUrl: String = provideBaseUrl()
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addConverterFactory(moshiConverterFactory)
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
}
