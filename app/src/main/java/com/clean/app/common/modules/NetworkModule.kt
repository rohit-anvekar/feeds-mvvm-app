package com.clean.app.common.modules

import com.clean.app.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by rohit.anvekar on 14/7/20.
 */
@Module
object NetworkModule {

    private const val CONNECTION_TIMEOUT = 10
    private const val READ_TIMEOUT = 60
    private const val WRITE_TIMEOUT = 60


    /**
     * provides an Interceptor object to enable logging http request/response,
     * based on the defined log level
     *
     */
    @Provides
    @Singleton
    @JvmStatic
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    /**
     * provides a custom OkHTPP object to be used a retrofit client
     * it could be used as a standalone http client
     */
    @Provides
    @Singleton
    @JvmStatic
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)


        //Enable logging in debug mode only
        if (BuildConfig.DEBUG) {
            clientBuilder.addInterceptor(loggingInterceptor)
        }
        return clientBuilder.build()
    }

}