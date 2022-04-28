package com.ineedyourcode.githubapiapp.domain.usecase

import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserSearchResult
import io.reactivex.rxjava3.core.Single

interface SearchGitHubUserUsecase {
    fun searchUser(
        searchingRequest: String,
    ): Single<GitHubUserSearchResult>

    fun getMostPopularUsers(): Single<GitHubUserSearchResult>
}