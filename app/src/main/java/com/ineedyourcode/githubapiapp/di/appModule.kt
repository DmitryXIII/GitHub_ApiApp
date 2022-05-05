package com.ineedyourcode.githubapiapp.di

import com.google.gson.GsonBuilder
import com.ineedyourcode.githubapiapp.data.datasource.mock.MockDataSource
import com.ineedyourcode.githubapiapp.data.datasource.retrofit.RetrofitDataSource
import com.ineedyourcode.githubapiapp.data.datasource.retrofit.RetrofitGitHubApi
import com.ineedyourcode.githubapiapp.data.datasource.retrofit.dtomapper.DtoMapper
import com.ineedyourcode.githubapiapp.data.repository.DataRepository
import com.ineedyourcode.githubapiapp.domain.usecase.GetProjectRepositoryUsecase
import com.ineedyourcode.githubapiapp.domain.usecase.GetUserUsecase
import com.ineedyourcode.githubapiapp.domain.usecase.SearchUserUsecase
import com.ineedyourcode.githubapiapp.ui.screens.userdetails.viewmodel.UserDetailsViewModel
import com.ineedyourcode.githubapiapp.ui.screens.userrepositorydetails.viewmodel.UserGitHubRepositoryViewModel
import com.ineedyourcode.githubapiapp.ui.screens.usersearch.viewmodel.UserSearchViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private enum class DataSourceType {
    MOCK,
    RETROFIT,
}

private val dataSourceType = DataSourceType.RETROFIT

val appModule = module {
    single(named("base_url")) { "https://api.github.com/" }
    single<GetUserUsecase> { DataRepository(get()) }
    single<SearchUserUsecase> { DataRepository(get()) }
    single<GetProjectRepositoryUsecase> { DataRepository(get()) }
    single<CallAdapter.Factory> { RxJava3CallAdapterFactory.create() }
    single<Converter.Factory> { GsonConverterFactory.create(GsonBuilder().setLenient().create()) }
    single<Interceptor> { HttpLoggingInterceptor().setLevel(get()) }
    single { HttpLoggingInterceptor.Level.BODY }
    single { OkHttpClient.Builder()
        .addInterceptor(get<Interceptor>())
        .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named("base_url")))
            .addConverterFactory(get())
            .addCallAdapterFactory(get())
            .client(get())
            .build()
            .create(RetrofitGitHubApi::class.java)
    }

    single {
        when (dataSourceType) {
            DataSourceType.MOCK -> {
                MockDataSource()
            }
            DataSourceType.RETROFIT -> {
                RetrofitDataSource(get(), get())
            }
        }
    }

    factory { DtoMapper() }

    viewModel { UserDetailsViewModel(get()) }
    viewModel { UserSearchViewModel(get()) }
    viewModel { UserGitHubRepositoryViewModel(get()) }
}