package com.ineedyourcode.githubapiapp.domain.usecase

import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserRepository
import io.reactivex.rxjava3.core.Single

interface GetGitHubRepositoryUsecase {
    fun getGitHubRepository(
        repoOwnerLogin: String,
        repoName: String
    ) : Single<GitHubUserRepository>
}