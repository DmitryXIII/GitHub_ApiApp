package com.ineedyourcode.githubapiapp.data

import com.ineedyourcode.githubapiapp.data.retrofit.RetrofitGitHubRepository
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserProfile
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserRepository
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserSearchResult
import com.ineedyourcode.githubapiapp.domain.githubapi.GitHubApi

class DataRepository : GitHubApi {
    private val dataSource: RetrofitGitHubRepository = RetrofitGitHubRepository()

    override fun searchUser(
        searchingRequest: String,
        callback: GitHubApi.GitHubCallback<GitHubUserSearchResult>,
    ) {
        dataSource.searchUser(searchingRequest,
            object : GitHubApi.GitHubCallback<GitHubUserSearchResult> {
                override fun onSuccess(result: GitHubUserSearchResult) {
                    callback.onSuccess(result)
                }

                override fun onFail(error: Throwable) {
                    callback.onFail(error)
                }
            })
    }

    override fun getMostPopularUsers(callback: GitHubApi.GitHubCallback<GitHubUserSearchResult>) {
        dataSource.getMostPopularUsers(object : GitHubApi.GitHubCallback<GitHubUserSearchResult> {
            override fun onSuccess(result: GitHubUserSearchResult) {
                callback.onSuccess(result)
            }

            override fun onFail(error: Throwable) {
                callback.onFail(error)
            }
        })
    }

    override fun getGitHubUser(
        login: String,
        callback: GitHubApi.GitHubCallback<GitHubUserProfile>,
    ) {
        dataSource.getGitHubUser(login, object : GitHubApi.GitHubCallback<GitHubUserProfile> {
            override fun onSuccess(result: GitHubUserProfile) {
                callback.onSuccess(result)
            }

            override fun onFail(error: Throwable) {
                callback.onFail(error)
            }
        })
    }

    override fun getGitHubUserRepositoriesList(
        login: String,
        callback: GitHubApi.GitHubCallback<List<GitHubUserRepository>>,
    ) {
        dataSource.getGitHubUserRepositoriesList(login,
            object : GitHubApi.GitHubCallback<List<GitHubUserRepository>> {
                override fun onSuccess(result: List<GitHubUserRepository>) {
                    callback.onSuccess(result)
                }

                override fun onFail(error: Throwable) {
                    callback.onFail(error)
                }
            })
    }

    override fun getGitHubRepository(
        repoOwnerLogin: String,
        repoName: String,
        callback: GitHubApi.GitHubCallback<GitHubUserRepository>,
    ) {
        dataSource.getGitHubRepository(repoOwnerLogin,
            repoName,
            object : GitHubApi.GitHubCallback<GitHubUserRepository> {
                override fun onSuccess(result: GitHubUserRepository) {
                    callback.onSuccess(result)
                }

                override fun onFail(error: Throwable) {
                    callback.onFail(error)
                }
            })
    }
}