package com.ineedyourcode.githubapiapp.di

import com.ineedyourcode.githubapiapp.data.datasource.mock.MockDataSource
import com.ineedyourcode.githubapiapp.data.datasource.retrofit.RetrofitDataSource
import com.ineedyourcode.githubapiapp.data.datasource.retrofit.RetrofitGitHubApi
import com.ineedyourcode.githubapiapp.data.datasource.retrofit.dtomapper.DtoMapper
import com.ineedyourcode.githubapiapp.data.repository.DataRepository
import com.ineedyourcode.githubapiapp.domain.repository.UsecaseRepository
import com.ineedyourcode.githubapiapp.domain.usecase.GetProjectRepositoryUsecase
import com.ineedyourcode.githubapiapp.domain.usecase.GetUserUsecase
import com.ineedyourcode.githubapiapp.domain.usecase.SearchUserUsecase
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

private const val MOCK_TYPE_NAME = "mock"
private const val DATA_REPOSITORY_NAME = "data_repository"
private const val RETROFIT_TYPE_NAME = "retrofit"
//private const val BASE_GIT_HUB_API_URL = "https://api.github.com/"

//private lateinit var requiredDataSourceType: String
//
//private enum class DataSourceType {
//    MOCK,
//    RETROFIT,
//}

//private val dataSourceType = DataSourceType.MOCK
//
//private fun checkDataSourceType() {
//    requiredDataSourceType = when (dataSourceType) {
//        DataSourceType.MOCK -> {
//            MOCK_TYPE_NAME
//        }
//        DataSourceType.RETROFIT -> {
//            RETROFIT_TYPE_NAME
//        }
//    }
//}

@Module
class AppDependenciesModule {
    @Singleton
    @Provides
    @Named(DATA_REPOSITORY_NAME)
    fun provideDataRepository(@Named(RETROFIT_TYPE_NAME) dataSource: UsecaseRepository): UsecaseRepository {
        return DataRepository(dataSource)
    }

    @Singleton
    @Provides
    @Named(MOCK_TYPE_NAME)
    fun provideMockDataSource(): UsecaseRepository {
        return MockDataSource()
    }

    @Singleton
    @Provides
    @Named(RETROFIT_TYPE_NAME)
    fun provideRetrofitDataSource(
        retrofit: RetrofitGitHubApi,
        dtoMapper: DtoMapper,
    ): UsecaseRepository {
        return RetrofitDataSource(retrofit, dtoMapper)
    }

    @Singleton
    @Provides
    fun provideUserDetailsRepository(@Named(RETROFIT_TYPE_NAME) dataSource: UsecaseRepository): GetUserUsecase {
        return DataRepository(dataSource)
    }

    @Singleton
    @Provides
    fun provideUserSearchRepository(@Named(RETROFIT_TYPE_NAME) dataSource: UsecaseRepository): SearchUserUsecase {
        return DataRepository(dataSource)
    }

    @Singleton
    @Provides
    fun provideUserProjectRepository(@Named(RETROFIT_TYPE_NAME) dataSource: UsecaseRepository): GetProjectRepositoryUsecase {
        return DataRepository(dataSource)
    }

    @Provides
    fun provideDtoMapper(): DtoMapper {
        return DtoMapper()
    }
}
