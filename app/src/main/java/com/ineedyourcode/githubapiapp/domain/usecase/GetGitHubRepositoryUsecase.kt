package com.ineedyourcode.githubapiapp.domain.usecase

import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserRepository
import retrofit2.Callback

interface GetGitHubRepositoryUsecase {
    fun getGitHubRepository(
        repoOwnerLogin: String,
        repoName: String,
        callback: Callback<GitHubUserRepository>,
    )
}