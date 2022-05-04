package com.ineedyourcode.githubapiapp.data.repository

import com.ineedyourcode.githubapiapp.data.datasourse.mock.MockDataSource
import com.ineedyourcode.githubapiapp.data.datasourse.retrofit.RetrofitDataSource
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserProfile
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserRepository
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserSearchResult
import com.ineedyourcode.githubapiapp.domain.githubapi.GitHubApi
import io.reactivex.rxjava3.core.Single

private enum class DataSourceType {
    MOCK,
    RETROFIT,
}

class DataRepository : GitHubApi {
    private val dataSourceType: DataSourceType = DataSourceType.RETROFIT

    private val dataSource = when (dataSourceType) {
        DataSourceType.MOCK -> {
            MockDataSource()
        }

        DataSourceType.RETROFIT -> {
            RetrofitDataSource()
        }
    }

    override fun searchUser(searchingRequest: String): Single<GitHubUserSearchResult> {
        return dataSource.searchUser(searchingRequest)
    }

    override fun getMostPopularUsers(): Single<GitHubUserSearchResult> {
        return dataSource.getMostPopularUsers()
    }

    override fun getGitHubUser(login: String): Single<GitHubUserProfile> {
        return dataSource.getGitHubUser(login)
    }

    override fun getGitHubUserRepositoriesList(
        login: String,
    ): Single<List<GitHubUserRepository>> {
        return dataSource.getGitHubUserRepositoriesList(login)
    }

    override fun getGitHubRepository(
        repoOwnerLogin: String,
        repoName: String,
    ): Single<GitHubUserRepository> {
        return dataSource.getGitHubRepository(repoOwnerLogin, repoName)
    }
}