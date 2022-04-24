package com.ineedyourcode.githubapiapp.domain.githubapi

import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserProfile
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserRepository
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserSearchResult
import retrofit2.Callback

interface GitHubApi {
    fun getUser(login: String, callback: Callback<GitHubUserProfile>)

    fun searchUsers(searchingRequest: String, callback: Callback<GitHubUserSearchResult>)

    fun getUserGitHubRepositories(login: String, callback: Callback<List<GitHubUserRepository>>)

    fun getGitHubRepository(repoOwnerLogin: String, repoName: String, callback: Callback<GitHubUserRepository>)

    fun getMostPopularUsers(callback: Callback<GitHubUserSearchResult>)
}