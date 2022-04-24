package com.ineedyourcode.githubapiapp.domain.usecase

import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserSearchResult
import retrofit2.Callback

interface SearchGitHubUserUsecase {
    fun searchUsers(searchingRequest: String, callback: Callback<GitHubUserSearchResult>)
    fun getMostPopularUsers(callback: Callback<GitHubUserSearchResult>)
}