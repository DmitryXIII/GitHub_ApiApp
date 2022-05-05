package com.ineedyourcode.githubapiapp.data.repository

import com.ineedyourcode.githubapiapp.data.datasource.mock.MockDataSource
import com.ineedyourcode.githubapiapp.data.datasource.retrofit.RetrofitDataSource
import com.ineedyourcode.githubapiapp.domain.entity.UserProfile
import com.ineedyourcode.githubapiapp.domain.entity.UserProjectRepository
import com.ineedyourcode.githubapiapp.domain.repository.UsecaseRepository
import io.reactivex.rxjava3.core.Single

private enum class DataSourceType {
    MOCK,
    RETROFIT,
}

class DataRepository : UsecaseRepository {
    private val dataSourceType: DataSourceType = DataSourceType.RETROFIT

    private val dataSource = when (dataSourceType) {
        DataSourceType.MOCK -> {
            MockDataSource()
        }

        DataSourceType.RETROFIT -> {
            RetrofitDataSource()
        }
    }

    override fun searchUser(searchingRequest: String): Single<List<UserProfile>> {
        return dataSource.searchUser(searchingRequest)
    }

    override fun getMostPopularUsers(): Single<List<UserProfile>> {
        return dataSource.getMostPopularUsers()
    }

    override fun getUserProfile(login: String): Single<UserProfile> {
        return dataSource.getUserProfile(login)
    }

    override fun getUserRepositoriesList(
        login: String,
    ): Single<List<UserProjectRepository>> {
        return dataSource.getUserRepositoriesList(login)
    }

    override fun getProjectRepository(
        repoOwnerLogin: String,
        repoName: String,
    ): Single<UserProjectRepository> {
        return dataSource.getProjectRepository(repoOwnerLogin, repoName)
    }
}