package com.ineedyourcode.githubapiapp.domain.usecase

import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserProfile
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserRepository
import retrofit2.Callback

interface GetGitHubUserUsecase {
    fun getUser(login: String, callback: Callback<GitHubUserProfile>)
    fun getUserGitHubRepositories(login: String, callback: Callback<List<GitHubUserRepository>>)
}