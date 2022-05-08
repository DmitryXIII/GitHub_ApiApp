package com.ineedyourcode.githubapiapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ineedyourcode.githubapiapp.data.datasource.retrofit.RetrofitGitHubApi
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_GIT_HUB_API_URL = "https://api.github.com/"

@Module
class RetrofitDependenciesModule {
    @Singleton
    @Provides
    fun provideRetrofit(
        baseUrl: String,
        converterFactory: Converter.Factory,
        callAdapterFactory: CallAdapter.Factory,
        client: OkHttpClient,
        gitHubApi: Class<RetrofitGitHubApi>,
    ): RetrofitGitHubApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .client(client)
            .build()
            .create(gitHubApi)
    }

    @Provides
    fun provideBaseUrl(): String {
        return BASE_GIT_HUB_API_URL
    }

    @Provides
    fun provideConverterFactory(gsonBuilder: Gson): Converter.Factory {
        return GsonConverterFactory.create(gsonBuilder)
    }

    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Provides
    fun provideCallAdapterFactory(): CallAdapter.Factory {
        return RxJava3CallAdapterFactory.create()
    }

    @Provides
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    fun provideOkHttpClientInterceptor(level: HttpLoggingInterceptor.Level): Interceptor {
        return HttpLoggingInterceptor().setLevel(level)
    }

    @Provides
    fun provideInterceptorLoggingLevel():
            HttpLoggingInterceptor.Level {
        return HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideGitHubApi(): Class<RetrofitGitHubApi> {
        return RetrofitGitHubApi::class.java
    }
}