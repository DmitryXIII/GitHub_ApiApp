package com.ineedyourcode.githubapiapp.data.retrofit

import com.ineedyourcode.githubapiapp.data.dto.GitHubUserProfileDto
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserRepositoryDto
import retrofit2.Callback

interface IRetrofitGitHubRepository {

    fun getUser(login: String, callback: Callback<GitHubUserProfileDto>)

    fun searchUsers(searchingRequest: String, callback: Callback<List<GitHubUserProfileDto>>)

    fun getUserRepositories(login: String, callback: Callback<List<GitHubUserRepositoryDto>>)

    fun getRepository(repoOwnerLogin: String, repoName: String, callback: Callback<GitHubUserRepositoryDto>)
}