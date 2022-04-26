package com.ineedyourcode.githubapiapp.data.usecase

import com.ineedyourcode.githubapiapp.data.dto.GitHubUserRepositoryDto
import com.ineedyourcode.githubapiapp.data.repository.DataCallback

interface DataGetGitHubRepositoryUsecase {
    fun getGitHubRepository(
        repoOwnerLogin: String,
        repoName: String,
        callback: DataCallback<GitHubUserRepositoryDto>,
    )
}