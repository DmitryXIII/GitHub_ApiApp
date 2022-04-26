package com.ineedyourcode.githubapiapp.data.usecase

import com.ineedyourcode.githubapiapp.data.dto.GitHubUserProfileDto
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserRepositoryDto
import com.ineedyourcode.githubapiapp.data.repository.DataCallback

interface DataGetGitHubUserUsecase {
    fun getGitHubUser(
        login: String,
        callback: DataCallback<GitHubUserProfileDto>,
    )

    fun getGitHubUserRepositoriesList(
        login: String,
        callback: DataCallback<List<GitHubUserRepositoryDto>>,
    )
}