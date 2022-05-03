package com.ineedyourcode.githubapiapp.data.usecase

import com.ineedyourcode.githubapiapp.data.dto.GitHubUserRepositoryDto
import io.reactivex.rxjava3.core.Single

interface DataGetGitHubRepositoryUsecase {
    fun getGitHubRepository(
        repoOwnerLogin: String,
        repoName: String,
    ): Single<GitHubUserRepositoryDto>
}