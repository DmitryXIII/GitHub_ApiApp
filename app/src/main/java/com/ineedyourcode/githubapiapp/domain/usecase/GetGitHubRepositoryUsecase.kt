package com.ineedyourcode.githubapiapp.domain.usecase

import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserRepository
import com.ineedyourcode.githubapiapp.domain.githubapi.GitHubApi

interface GetGitHubRepositoryUsecase {
    fun getGitHubRepository(
        repoOwnerLogin: String,
        repoName: String,
        callback: GitHubApi.GitHubCallback<GitHubUserRepository>,
    )
}