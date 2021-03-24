package com.n.supernytimesparser.application.di.module

import com.n.supernytimesparser.application.SuperNYTimesParserRestApi
import com.n.supernytimesparser.application.utils.network.AuthInterceptor
import com.n.supernytimesparser.application.utils.resource.ResourceManager
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Module for Network
 *
 * @author Pavel Apanovskii on 23/03/2021.
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        resourceManager: ResourceManager
    ): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val builder: Retrofit.Builder = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(HTTP_SCHEMA + BASE_API)
        val client: OkHttpClient = Builder()
            .addInterceptor(AuthInterceptor(resourceManager))
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(interceptor).build()
        return builder
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): SuperNYTimesParserRestApi {
        return retrofit.create(SuperNYTimesParserRestApi::class.java)
    }

    companion object {

        const val HTTP_SCHEMA = "https://"
        const val BASE_API = "api.nytimes.com/"
    }
}