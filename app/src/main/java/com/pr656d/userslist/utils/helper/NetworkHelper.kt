package com.pr656d.userslist.utils.helper

import android.content.Context
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.gsonparserfactory.GsonParserFactory
import com.pr656d.userslist.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

/**
 * Helper for network.
 */
object NetworkHelper {
    /**  Network call timeout  */
    private const val NETWORK_CALL_TIMEOUT: Long = 60

    /**  Network cache size  */
    private const val cacheSize: Long = 10 * 1024 * 1024 // 10MB

    /**
     * Setup networking library.
     */
    fun initialize(context: Context) {
        // Intercept network calls to log.
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .apply {
                level = if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE
            }

        // OkHttpClient
        val okHttpClient = OkHttpClient.Builder()
            .cache(Cache(context.cacheDir,
                cacheSize
            ))
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS)
            .build()

        // Initialize Networking
        AndroidNetworking.initialize(context, okHttpClient)

        // Set Gson parser
        AndroidNetworking.setParserFactory(GsonParserFactory())
    }
}