package com.ineedyourcode.githubapiapp.data.repository

import com.ineedyourcode.githubapiapp.data.datasourse.mock.MockRepository
import com.ineedyourcode.githubapiapp.data.datasourse.retrofit.RetrofitGitHubRepository
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserProfileDto
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserRepositoryDto
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserSearchResultDto
import com.ineedyourcode.githubapiapp.data.utils.convertGitHubSearchResultEntityToDto
import com.ineedyourcode.githubapiapp.data.utils.convertGitHubUserEntityToDto
import com.ineedyourcode.githubapiapp.data.utils.convertGitHubUserRepositoriesEntityListToDto
import com.ineedyourcode.githubapiapp.data.utils.convertGitHubUserRepositoryEntityToDto
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserProfile
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserRepository
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserSearchResult
import com.ineedyourcode.githubapiapp.domain.githubapi.GitHubApi

private enum class DataSourceType {
    DATA_SOURCE_TYPE_MOCK,
    DATA_SOURCE_TYPE_RETROFIT,
}

class DataRepository : IDataRepository {
    private val dataSourceType: DataSourceType = DataSourceType.DATA_SOURCE_TYPE_RETROFIT

    private val dataSource = when (dataSourceType) {
        DataSourceType.DATA_SOURCE_TYPE_MOCK -> {
            MockRepository()
        }

        DataSourceType.DATA_SOURCE_TYPE_RETROFIT -> {
            RetrofitGitHubRepository()
        }
    }

    override fun searchUser(
        searchingRequest: String,
        callback: DataCallback<GitHubUserSearchResultDto>,
    ) {
        dataSource.searchUser(searchingRequest,
            object : GitHubApi.GitHubCallback<GitHubUserSearchResult> {
                override fun onSuccess(result: GitHubUserSearchResult) {
                    callback.onSuccess(convertGitHubSearchResultEntityToDto(result))
                }

                override fun onFail(error: Throwable) {
                    callback.onFail(error)
                }
            })
    }

    override fun getMostPopularUsers(callback: DataCallback<GitHubUserSearchResultDto>) {
        dataSource.getMostPopularUsers(object : GitHubApi.GitHubCallback<GitHubUserSearchResult> {
            override fun onSuccess(result: GitHubUserSearchResult) {
                callback.onSuccess(convertGitHubSearchResultEntityToDto(result))
            }

            override fun onFail(error: Throwable) {
                callback.onFail(error)
            }
        })
    }

    override fun getGitHubUser(
        login: String,
        callback: DataCallback<GitHubUserProfileDto>,
    ) {
        dataSource.getGitHubUser(login, object : GitHubApi.GitHubCallback<GitHubUserProfile> {
            override fun onSuccess(result: GitHubUserProfile) {
                callback.onSuccess(convertGitHubUserEntityToDto(result))
            }

            override fun onFail(error: Throwable) {
                callback.onFail(error)
            }
        })
    }

    override fun getGitHubUserRepositoriesList(
        login: String,
        callback: DataCallback<List<GitHubUserRepositoryDto>>,
    ) {
        dataSource.getGitHubUserRepositoriesList(login,
            object : GitHubApi.GitHubCallback<List<GitHubUserRepository>> {
                override fun onSuccess(result: List<GitHubUserRepository>) {
                    callback.onSuccess(convertGitHubUserRepositoriesEntityListToDto(result))
                }

                override fun onFail(error: Throwable) {
                    callback.onFail(error)
                }
            })
    }

    override fun getGitHubRepository(
        repoOwnerLogin: String,
        repoName: String,
        callback: DataCallback<GitHubUserRepositoryDto>,
    ) {
        dataSource.getGitHubRepository(repoOwnerLogin,
            repoName,
            object : GitHubApi.GitHubCallback<GitHubUserRepository> {
                override fun onSuccess(result: GitHubUserRepository) {
                    callback.onSuccess(convertGitHubUserRepositoryEntityToDto(result))
                }

                override fun onFail(error: Throwable) {
                    callback.onFail(error)
                }
            })
    }
}