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
private const val RETROFIT_TYPE_NAME = "retrofit"

private const val REQUIRED_DATA_SOURCE_TYPE = MOCK_TYPE_NAME

@Module
class RepositoriesDependenciesModule {
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
    fun provideUserDetailsRepository(@Named(REQUIRED_DATA_SOURCE_TYPE) dataSource: UsecaseRepository): GetUserUsecase {
        return DataRepository(dataSource)
    }

    @Singleton
    @Provides
    fun provideUserSearchRepository(@Named(REQUIRED_DATA_SOURCE_TYPE) dataSource: UsecaseRepository): SearchUserUsecase {
        return DataRepository(dataSource)
    }

    @Singleton
    @Provides
    fun provideUserProjectRepository(@Named(REQUIRED_DATA_SOURCE_TYPE) dataSource: UsecaseRepository): GetProjectRepositoryUsecase {
        return DataRepository(dataSource)
    }

    @Provides
    fun provideDtoMapper(): DtoMapper {
        return DtoMapper()
    }
}
