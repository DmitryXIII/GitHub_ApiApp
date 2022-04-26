package com.ineedyourcode.githubapiapp.domain.usecase

import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserSearchResult
import com.ineedyourcode.githubapiapp.domain.githubapi.GitHubApi

interface SearchGitHubUserUsecase {
    fun searchUser(
        searchingRequest: String,
        callback: GitHubApi.GitHubCallback<GitHubUserSearchResult>,
    )

    fun getMostPopularUsers(
        callback: GitHubApi.GitHubCallback<GitHubUserSearchResult>,
    )
}